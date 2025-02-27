import { createSlice } from "@reduxjs/toolkit";

const initialState = { token: null };

const userSlice = createSlice({
  name: "userSlice",
  initialState,
  reducers: {
    userLoggedIn(state, { payload }) {
      state.token = payload.token;
    },
    userLoggedOut(state) {
      state.token = null;
    },
  },
});
export const selectLoggedInUser = (state) => state.userSlice?.token;

export default userSlice.reducer;

export const { userLoggedIn, userLoggedOut } = userSlice.actions;
