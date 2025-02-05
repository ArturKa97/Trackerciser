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
    text: {
      primary: "#fafafa",
    },
  },
  typography: {
    fontFamily: "'Roboto', sans-serif",

    h1: {
      fontSize: "5.2rem",
      fontWeight: 600,
    },

    h2: {
      fontSize: "2.4rem",
      fontWeight: 500,
    },
  },
  components: {
    MuiCssBaseline: {
      styleOverrides: {
        body: {
          backgroundImage: ` linear-gradient(
              rgba(0, 0, 0, 0.7), 
              rgba(0, 0, 0, 0.7)
            ),url(${background})`,
          backgroundRepeat: "no-repeat",
          backgroundSize: "cover",
          minHeight: "100vh",
          backgroundPosition: "center",
        },
      },
    },

    MuiTableHead: {
      styleOverrides: {
        root: ({ theme }) => ({
          backgroundColor: theme.palette.primary.main,
          borderBottom: "2px solid #fafafa",
        }),
      },
    },
    MuiTableBody: {
      styleOverrides: {
        root: ({ theme }) => ({
          backgroundColor: theme.palette.primary.light,
        }),
      },
    },
    MuiTableRow: {
      styleOverrides: {
        root: ({ theme }) => ({
          borderBottom: "2px solid #fafafa",
          transition: "background-color 0.3s ease-in-out",
          "&:hover": {
            backgroundColor: theme.palette.primary.main,
          },
        }),
      },
    },
    MuiTableCell: {
      styleOverrides: {
        head: ({ theme }) => ({
          color: theme.palette.text.primary,
          fontSize: "1.2rem",
          fontWeight: 600,
          textTransform: "uppercase",
        }),
        body: ({ theme }) => ({
          color: theme.palette.text.primary,
          fontSize: "1rem",
        }),
      },
    },
  },
});

export default AppTheme;
