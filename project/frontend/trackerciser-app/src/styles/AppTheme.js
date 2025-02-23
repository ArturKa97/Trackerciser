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
  breakpoints: {
    values: {
      xs: 0,
      sm: 600,
      md: 1100,
      lg: 1200,
    },
  },
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
      "@media (max-width: 1100px)": {
        fontSize: "4.4rem", // Adjusted size for "md" screens and below
      },
    },

    h2: {
      fontSize: "2.4rem",
      fontWeight: 500,
      "@media (max-width: 1100px)": {
        fontSize: "2.0rem", // Adjusted size for "md" screens and below
      },
    },
    h3: {
      fontSize: "1.8rem",
      fontWeight: 500,
    },
    h4: {
      fontSize: "1.4rem",
      fontWeight: 400,
    },
    h5: {
      fontSize: "1.2rem",
      fontWeight: 400,
    },
    h6: {
      fontSize: "1.0rem",
      fontWeight: 400,
    },
    h7: {
      fontSize: "0.8rem",
      fontWeight: 400,
    },
  },
  components: {
    MuiCssBaseline: {
      styleOverrides: {
        body: {
          backgroundImage: ` linear-gradient(
              rgba(0, 0, 0, 0.8), 
              rgba(0, 0, 0, 0.8)
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
          borderBottom: `2px solid ${theme.palette.primary.contrastText}`,
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
          borderBottom: `2px solid ${theme.palette.primary.contrastText}`,
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
          textAlign: "left",
        }),
        body: ({ theme }) => ({
          color: theme.palette.text.primary,
          fontSize: "1rem",
          textAlign: "left",
        }),
      },
    },
    MuiTextField: {
      styleOverrides: {
        root: ({ theme }) => ({
          "& .MuiOutlinedInput-root": {
            "& .MuiOutlinedInput-notchedOutline": {
              borderColor: theme.palette.primary.contrastText,
            },
            "&:hover .MuiOutlinedInput-notchedOutline": {
              boxShadow: `0 0 0 1px ${theme.palette.primary.contrastText}`,
              transition: "box-shadow 0.3s ease-in-out",
            },
            "&:hover.Mui-error .MuiOutlinedInput-notchedOutline": {
              boxShadow: `0 0 0 1px ${theme.palette.secondary.main}`,
              transition: "box-shadow 0.3s ease-in-out",
            },
            "&.Mui-focused .MuiOutlinedInput-notchedOutline": {
              boxShadow: `0 0 0 2px ${theme.palette.primary.contrastText}`,
              transition: "box-shadow 0.3s ease-in-out",
            },
            "&.Mui-focused.Mui-error .MuiOutlinedInput-notchedOutline": {
              boxShadow: `0 0 0 2px ${theme.palette.secondary.main}`,
              transition: "box-shadow 0.3s ease-in-out",
            },
          },
          "& .MuiInputLabel-root": {
            color: theme.palette.primary.contrastText,
          },
          "& .MuiInputLabel-root.Mui-error.Mui-focused": {
            color: theme.palette.secondary.main,
          },
          "& .MuiInputLabel-root.Mui-focused": {
            color: theme.palette.primary.contrastText,
          },
        }),
      },
    },
    MuiSelect: {
      styleOverrides: {
        root: ({ theme }) => ({
          backgroundColor: theme.palette.primary.light,
          color: theme.palette.primary.contrastText,
          border: "1px solid #fafafa",
          boxShadow: "none",
          transition: "box-shadow 0.3s ease-in-out",
          "&:hover": {
            boxShadow: `0 0 0 1px ${theme.palette.primary.contrastText}`,
          },
          "&.Mui-focused": {
            boxShadow: `0 0 0 2px ${theme.palette.primary.contrastText}`,
          },
        }),
      },
      defaultProps: {
        MenuProps: {
          PaperProps: {
            sx: {
              backgroundColor: (theme) => theme.palette.primary.light,
            },
          },
        },
      },
    },
    MuiMenu: {
      styleOverrides: {
        paper: ({ theme }) => ({
          backgroundColor: theme.palette.primary.light,
        }),
      },
    },
    MuiMenuItem: {
      styleOverrides: {
        root: ({ theme }) => ({
          backgroundColor: theme.palette.primary.light,
          color: theme.palette.primary.contrastText,
          transition: "background-color 0.3s ease-in-out",
          "&:hover": {
            backgroundColor: theme.palette.primary.main,
            color: theme.palette.primary.contrastText,
          },
          "&.Mui-selected": {
            backgroundColor: theme.palette.primary.main,
            color: theme.palette.primary.contrastText,
          },
        }),
      },
    },
    MuiPaginationItem: {
      styleOverrides: {
        root: ({ theme }) => ({
          boxShadow: `0 0 0 1px ${theme.palette.primary.contrastText}`,

          fontSize: "1.0rem",
          backgroundColor: theme.palette.primary.light,
          color: theme.palette.primary.contrastText,
          transition: "background-color 0.3s ease-in-out",
          "&:hover": {
            transition: "box-shadow 0.3s ease-in-out",
            backgroundColor: theme.palette.primary.main,
          },
          "&.Mui-selected": {
            transition: "box-shadow 0.3s ease-in-out",
            boxShadow: `0 0 0 2px ${theme.palette.primary.contrastText}`,
            backgroundColor: theme.palette.primary.main,
            color: theme.palette.primary.contrastText,
          },
        }),
      },
    },
  },
});

export default AppTheme;
