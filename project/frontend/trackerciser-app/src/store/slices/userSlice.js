import { createSlice } from "@reduxjs/toolkit";

const initialState = localStorage.getItem("token")
  ? { token: localStorage.getItem("token") }
  : null;

const userSlice = createSlice({
  name: "userSlice",
  initialState,
  reducers: {
    userLoggedIn(userState, { payload: loggedInUser }) {
      localStorage.setItem("token", loggedInUser.token);
      return loggedInUser;
    },
    userLoggedOut() {
      localStorage.removeItem("token");
      return null;
    },
  },
});
export const selectLoggedInUser = (state) => state.userSlice?.token;

export default userSlice.reducer;

export const { userLoggedIn, userLoggedOut } = userSlice.actions;
