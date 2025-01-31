import { createTheme } from "@mui/material";
import background from "../img/background.jpg";

const AppTheme = createTheme({
  palette: {
    primary: {
      main: "#212121",
      light: "#424242",
      contrastText: "#fafafa",
    },
    secondary: {
      main: "#b71c1c",
      light: "#c62828",
      contrastText: "#212121",
    },
  },
  components: {
    MuiCssBaseline: {
      styleOverrides: {
        body: {
          backgroundImage: `url(${background})`,
          backgroundRepeat: "no-repeat",
          backgroundSize: "cover",
          height: "100vh",
          backgroundPosition: "center",
        },
    },
  },
}});

export default AppTheme;
