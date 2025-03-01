import { createSlice } from "@reduxjs/toolkit";

const storedUser = localStorage.getItem("userDTO") || "null";
const initialState = {
  token: null,
  userDTO: JSON.parse(storedUser),
};

const userSlice = createSlice({
  name: "userSlice",
  initialState,
  reducers: {
    userLoggedIn(state, { payload }) {
      state.token = payload.token;
      state.userDTO = payload.userDTO;
      localStorage.setItem("userDTO", JSON.stringify(payload.userDTO));
    },
    userLoggedOut(state) {
      state.token = null;
      state.userDTO = null;
      localStorage.removeItem("userDTO");
    },
  },
});
export const selectLoggedInUser = (state) => state.userSlice?.token;
export const selectUserDTO = (state) => state.userSlice?.userDTO;

export default userSlice.reducer;

export const { userLoggedIn, userLoggedOut } = userSlice.actions;
