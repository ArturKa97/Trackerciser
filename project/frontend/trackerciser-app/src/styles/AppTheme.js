import { createTheme } from "@mui/material";
import background from "../img/background.jpg";

/*
--- TYPOGRAPHY SYSTEM

- Font sizes (px)
10 / 12 / 14 / 16 / 18 / 20 / 24 / 30 / 36 / 44 / 52 / 62 / 74 / 86 / 98

- Font weights
Default: 400
Medium: 500
Semi-bold: 600
Bold: 700
---  WHITESPACE

- Spacing system (px)
2 / 4 / 8 / 12 / 16 / 24 / 32 / 48 / 64 / 80 / 96 / 128
*/

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
  },
});

export default AppTheme;
