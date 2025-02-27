import axios from "axios";
import { userLoggedIn, userLoggedOut } from "../store/slices/userSlice";

const HTTP = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

const initBackendApiClient = (store) => {
  HTTP.interceptors.request.use((config) => {
    if (config.url !== "/refresh") {
      const jwt = store.getState().userSlice?.token;
      if (jwt) {
        config.headers.Authorization = "Bearer " + jwt;
      }
    }
    return config;
  });

  HTTP.interceptors.response.use(
    (response) => response,
    async (error) => {
      const originalRequest = error.config;
      if (!originalRequest) {
        return Promise.reject(error);
      }
      if (error.response?.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;
        try {
          const { data } = await HTTP.post(
            "/refresh",
            {},
            { withCredentials: true }
          );
          store.dispatch(userLoggedIn({ token: data.token }));
          originalRequest.headers.Authorization = `Bearer ${data.token}`;
          return HTTP.request(originalRequest);
        } catch (refreshError) {
          store.dispatch(userLoggedOut());
          return Promise.reject(refreshError);
        }
      }
      return Promise.reject(error);
    }
  );
};
export default HTTP;
export { initBackendApiClient };
