import { createTheme } from "@mui/material";

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
          maxWidth: "xl",
          justifyContent: "center",
          zIndex: (theme) => theme.zIndex.drawer + 1,
        },
      },
    },
    MuiToolbar: {
      styleOverrides: {
        root: {
          display: "flex",
          justifyContent: "flex-start",
          mr: "auto",
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        text: {
          color: "#fff",
          fontWeight: 550,
          "&:hover": {
            backgroundColor: "#424242",
          },
        },
      },
    },
    MuiPapper: {
      styleOverrides: {
        backgroundColor: "primary"

        },
      },
    },
  },
);

export default AppTheme;
