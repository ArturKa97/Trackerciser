import HTTP from "./";

export function registerNewUser(user) {
    return HTTP.post("/user", user);
  }

  export function loginAndAuthenticate(user) {
    return HTTP.post("/authenticate", user);
  }
  