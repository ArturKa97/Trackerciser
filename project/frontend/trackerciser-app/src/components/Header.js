import * as React from "react";
import { useState } from "react";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";
import MenuIcon from "@mui/icons-material/Menu";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import {
  selectLoggedInUser,
  selectUserDTO,
  userLoggedOut,
} from "../store/slices/userSlice";
import { useDispatch } from "react-redux";
import {
  FlexToolbar,
  HeaderButton,
  HeaderButtonBox,
  HeaderHomeButton,
  HeaderLogoBox,
  HeaderMenuBoxSm,
  HeaderMenuButton,
  LogoTypography,
  StyledAppBar,
} from "../styles/StyledComponents";
import { Box, Menu, MenuItem, Typography, useMediaQuery } from "@mui/material";
import { logoutUser } from "../api/UserApi";

function Header() {
  const user = useSelector(selectLoggedInUser);
  const userDTO = useSelector(selectUserDTO);
  const dispatch = useDispatch();
  const [anchor, setAnchor] = useState(null);
  const open = !!anchor;
  const isSm = useMediaQuery("(max-width:754px)");

  const logoutUserCall = async () => {
    try {
      await logoutUser();
      dispatch(userLoggedOut());
    } catch (error) {
      console.error(error);
    } finally {
      navigate("/");
    }
  };
  console.log(userDTO);

  const handleOpen = (event) => {
    setAnchor(event.currentTarget);
  };
  const handleClose = () => {
    setAnchor(null);
  };

  const navigate = useNavigate();

  return (
    <StyledAppBar>
      <FlexToolbar>
        {!isSm ? (
          <>
            <HeaderButtonBox>
              <HeaderLogoBox>
                <HeaderHomeButton onClick={() => navigate("/")}>
                  <FitnessCenterIcon />
                  <LogoTypography>TRACKERCISER</LogoTypography>
                </HeaderHomeButton>
              </HeaderLogoBox>
              {user && (
                <>
                  <HeaderButton onClick={() => navigate("/workoutSessions")}>
                    WORKOUT SESSIONS
                  </HeaderButton>
                  <HeaderButton onClick={() => navigate("/chart")}>
                    CHARTS
                  </HeaderButton>
                </>
              )}
            </HeaderButtonBox>
            <HeaderButtonBox>
              {!user ? (
                <>
                  <HeaderButton onClick={() => navigate("/login")}>
                    LOGIN
                  </HeaderButton>
                  <HeaderButton onClick={() => navigate("/register")}>
                    REGISTER
                  </HeaderButton>
                </>
              ) : (
                <HeaderButtonBox>
                  <Typography variant="h5">
                    Hello, {userDTO.username}!
                  </Typography>
                  <HeaderButton onClick={() => logoutUserCall()}>
                    LOG OUT
                  </HeaderButton>
                </HeaderButtonBox>
              )}
            </HeaderButtonBox>
          </>
        ) : (
          <HeaderMenuBoxSm>
            <HeaderLogoBox>
              <HeaderHomeButton onClick={() => navigate("/")}>
                <FitnessCenterIcon />
                <LogoTypography>TRACKERCISER</LogoTypography>
              </HeaderHomeButton>
            </HeaderLogoBox>
            {userDTO && (
              <HeaderButtonBox>
                <Typography variant="h5">Hello, {userDTO.username}!</Typography>
              </HeaderButtonBox>
            )}
            {/* Render only at 754px and below*/}

            <HeaderMenuButton onClick={handleOpen}>
              <MenuIcon />
            </HeaderMenuButton>
            <Menu open={open} anchorEl={anchor} onClose={handleClose}>
              {user && (
                <Box>
                  <MenuItem
                    onClick={() => {
                      navigate("/workoutSessions");
                      handleClose();
                    }}
                  >
                    WORKOUT SESSIONS
                  </MenuItem>
                  <MenuItem
                    onClick={() => {
                      navigate("/chart");
                      handleClose();
                    }}
                  >
                    CHARTS
                  </MenuItem>
                </Box>
              )}
              {!user ? (
                <Box>
                  <MenuItem
                    key="login"
                    onClick={() => {
                      navigate("/login");
                      handleClose();
                    }}
                  >
                    LOG IN
                  </MenuItem>
                  <MenuItem
                    key="register"
                    onClick={() => {
                      navigate("/register");
                      handleClose();
                    }}
                  >
                    REGISTER
                  </MenuItem>
                </Box>
              ) : (
                <MenuItem
                  key="logout"
                  onClick={() => {
                    logoutUserCall();
                    handleClose();
                  }}
                >
                  LOG OUT
                </MenuItem>
              )}
            </Menu>
          </HeaderMenuBoxSm>
        )}
      </FlexToolbar>
    </StyledAppBar>
  );
}
export default Header;
