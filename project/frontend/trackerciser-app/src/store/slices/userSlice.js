import {createSlice} from "@reduxjs/toolkit";

const initialState = null

const userSlice = createSlice({
        name: "userSlice",
        initialState,
        reducers: {
            userLoggedIn(userState, {payload: loggedInUser}) {
                return loggedInUser;
            },
            userLoggedOut(){
                return initialState
            }

        }
    }
)
export const selectLoggedInUser = state => state.userSlice?.token

export default userSlice.reducer;

export const { userLoggedIn, userLoggedOut } = userSlice.actions

