import * as React from "react";
import IconButton from "@mui/material/IconButton";
import Avatar from "@mui/material/Avatar";
import Tooltip from "@mui/material/Tooltip";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { selectLoggedInUser, userLoggedOut } from "../store/slices/userSlice";
import { useDispatch } from "react-redux";
import {
  FlexToolbar,
  HeaderButton,
  HeaderButtonBox,
  HeaderLogoBox,
  LogoTypography,
  StyledAppBar,
} from "../styles/StyledComponents";

function Header() {
  const user = useSelector(selectLoggedInUser);
  const dispatch = useDispatch();

  const navigate = useNavigate();

  return (
    <StyledAppBar>
      <FlexToolbar>
        <HeaderButtonBox>
          <HeaderLogoBox>
            <FitnessCenterIcon />
            <LogoTypography>TRACKERCISER</LogoTypography>
          </HeaderLogoBox>
          <HeaderButton onClick={() => navigate("/workoutSessions")}>
            Workout Sessions
          </HeaderButton>
          <HeaderButton onClick={() => navigate("/chart")}>Charts</HeaderButton>
        </HeaderButtonBox>
        <HeaderButtonBox>
          {!user ? (
            <>
              <HeaderButton onClick={() => navigate("/login")}>
                Login
              </HeaderButton>
              <HeaderButton onClick={() => navigate("/register")}>
                Register
              </HeaderButton>
            </>
          ) : (
            <HeaderButton
              onClick={() => {
                dispatch(userLoggedOut());
                navigate("/");
              }}
            >
              Log Out
            </HeaderButton>
          )}
        </HeaderButtonBox>
        {/* <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <IconButton sx={{ p: 0 }}>
                <Avatar sx={{ bgcolor: deepPurple[500] }}>PH</Avatar>
              </IconButton>
            </Tooltip>
          </Box> */}
      </FlexToolbar>
    </StyledAppBar>
  );
}
export default Header;
