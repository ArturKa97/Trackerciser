import axios from "axios";

const HTTP = axios.create({
  baseURL: "http://localhost:8080",
});

//TODO: Implement the token removal after token expirationDate ends.
const initBackendApiClient = (store) => {
  HTTP.interceptors.request.use((config) => {
    let jwt = store.getState().userSlice?.token;

    if (!jwt) {
      jwt = localStorage.getItem("token");
    }

    if (jwt) {
      config.headers.Authorization = "Bearer " + jwt;
    }

    return config;
  });
};
export default HTTP;
export { initBackendApiClient };
