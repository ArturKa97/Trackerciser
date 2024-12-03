import { createTheme } from "@mui/material";

const AppTheme = createTheme({
  palette: {
    primary: {
      main: "#212121",
    },
    secondary: {
      main: "#801313",
    },
  },
  typography: {
    h6: { fontFamily: "monospace", fontWeight: 700, letterSpacing: ".3rem" },
  },
  components: {
    MuiCssBaseline: {
      styleOverrides: {
        body: {
          backgroundImage:
            'url("https://web-back.perfectgym.com/sites/default/files/styles/900x/public/equipment%20%286%29.jpg?itok=UbaJqjIr")',
          backgroundSize: "cover",
          backgroundPosition: "center",
          backgroundRepeat: "no-repeat",
          height: "100vh",
          margin: 0,
        },
      },
    },
    MuiAppBar: {
      styleOverrides: {
        root: {
          height: "8vh",
          maxWidth: "xl"
        },
      },
    },
    MuiToolbar: {
      styleOverrides: {
        root: {
          display: "flex",
          justifyContent: "flex-start",
          mr: "auto"
        },
      },
    },
  },
});

export default AppTheme;
