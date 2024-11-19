import HTTP from "./";

export function registerNewUser(user) {
    return HTTP.post("/user", user);
  }
  