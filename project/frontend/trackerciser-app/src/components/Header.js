import * as React from "react";
import { useState } from "react";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";
import MenuIcon from "@mui/icons-material/Menu";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { selectLoggedInUser, userLoggedOut } from "../store/slices/userSlice";
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
import { Box, Menu, MenuItem, useMediaQuery } from "@mui/material";

function Header() {
  const user = useSelector(selectLoggedInUser);
  const dispatch = useDispatch();
  const [anchor, setAnchor] = useState(null);
  const open = !!anchor;
  const isSm = useMediaQuery("(max-width:754px)");

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
              <HeaderButton onClick={() => navigate("/workoutSessions")}>
                WORKOUT SESSIONS
              </HeaderButton>
              <HeaderButton onClick={() => navigate("/chart")}>
                CHARTS
              </HeaderButton>
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
                <HeaderButton
                  onClick={() => {
                    dispatch(userLoggedOut());
                    navigate("/");
                  }}
                >
                  LOG OUT
                </HeaderButton>
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
            {/* Render only at 754px and below*/}
            <HeaderMenuButton onClick={handleOpen}>
              <MenuIcon />
            </HeaderMenuButton>
            <Menu open={open} anchorEl={anchor} onClose={handleClose}>
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
                  onClick={() => {
                    dispatch(userLoggedOut());
                    navigate("/");
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
