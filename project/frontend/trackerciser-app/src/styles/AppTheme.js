import { createTheme } from "@mui/material";
import background from "../img/background.jpg";

const AppTheme = createTheme({
  palette: {
    primary: {
      main: "#212121",
      light: "#424242",
      contrastText: "#fff",
    },
    secondary: {
      main: "#801313",
      contrastText: "#fff",
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
