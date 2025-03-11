import HTTP from "./";

export function registerNewUser(user) {
  return HTTP.post("/user/register", user);
}

export function loginAndAuthenticate(user) {
  return HTTP.post("/authenticate", user).then((response) => {
    return response.data;
  });
}

export function logoutUser() {
  return HTTP.post("/logoutUser");
}
