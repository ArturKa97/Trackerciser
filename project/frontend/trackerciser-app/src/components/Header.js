import * as React from "react";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";
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
            <HeaderHomeButton onClick={() => navigate("/")}>
              <FitnessCenterIcon />
              <LogoTypography>TRACKERCISER</LogoTypography>
            </HeaderHomeButton>
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
      </FlexToolbar>
    </StyledAppBar>
  );
}
export default Header;
