import axios from "axios";
import { userLoggedIn, userLoggedOut } from "../store/slices/userSlice";

const HTTP = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

const initBackendApiClient = async (store) => {
  // Works on page refresh, because the token is in memory and gets reset after page refresh,
  //  so this checks if the refresh token is still valid and provides the new access token if so, if it's not we log the user out.
  try {
    const { data } = await HTTP.post("/refresh", {}, { withCredentials: true });
    store.dispatch(userLoggedIn({ token: data.token, userDTO: data.userDTO }));
  } catch (error) {
    store.dispatch(userLoggedOut());
  }

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
          const currentUserDTO = store.getState().userSlice?.userDTO;
          store.dispatch(
            userLoggedIn({
              token: data.token,
              userDTO: data.userDTO || currentUserDTO,
            })
          );
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
