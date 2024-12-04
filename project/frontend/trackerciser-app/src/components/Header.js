import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import Tooltip from "@mui/material/Tooltip";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";
import { deepPurple } from "@mui/material/colors";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { selectLoggedInUser, userLoggedOut } from "../store/slices/userSlice";
import { useDispatch } from "react-redux";

function Header() {
  const user = useSelector(selectLoggedInUser);
  const dispatch = useDispatch();

  const navigate = useNavigate();

  return (
    <AppBar position="sticky">
      <Container maxWidth="xl">
        <Toolbar>
          <FitnessCenterIcon sx={{ mr: 2 }} />
          <Typography variant="h6" noWrap sx={{ mr: 2 }}>
            TRACKERCISER
          </Typography>
          <Button
            variant="text"
            onClick={() => navigate("/workoutSessions")}
            sx={{ m: 1 }}
          >
            Workout Sessions
          </Button>
          <Button
            variant="text"
            onClick={() => navigate("/chart")}
            sx={{ m: 1 }}
          >
            Charts
          </Button>
          <Box sx={{ display: "flex", justifyContent: "flex-end", ml: "auto" }}>
            {!user ? (
              <>
                <Button
                  variant="text"
                  onClick={() => navigate("/login")}
                  sx={{ m: 1 }}
                >
                  Login
                </Button>
                <Button
                  variant="text"
                  onClick={() => navigate("/register")}
                  sx={{ m: 1 }}
                >
                  Register
                </Button>
              </>
            ) : (
              <Button
                variant="text"
                onClick={() => {
                  dispatch(userLoggedOut());
                  navigate("/");
                }}
                sx={{ m: 1 }}
              >
                Log Out
              </Button>
            )}
          </Box>
          {/* <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <IconButton sx={{ p: 0 }}>
                <Avatar sx={{ bgcolor: deepPurple[500] }}>PH</Avatar>
              </IconButton>
            </Tooltip>
          </Box> */}
        </Toolbar>
      </Container>
    </AppBar>
  );
}
export default Header;
