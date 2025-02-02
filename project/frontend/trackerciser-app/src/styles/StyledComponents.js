import { styled } from "@mui/material/styles";
import Button from "@mui/material/Button";
import {
  AppBar,
  Toolbar,
  Box,
  Typography,
  Container,
  Card,
  CardContent,
} from "@mui/material";

export const AppBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  minHeight: "100vh",
});

export const AppRoutesBox = styled(Box)({
  flex: 1,
});

export const StyledAppBar = styled(AppBar)({
  position: "sticky",
});

export const HeaderButton = styled(Button)(({ theme }) => ({
  color: theme.palette.primary.contrastText,
  fontSize: "1rem",
  "&:hover": {
    backgroundColor: theme.palette.primary.light,
  },
}));

export const FlexToolbar = styled(Toolbar)({
  display: "flex",
  justifyContent: "space-between",
  alignContent: "center",
});

export const HeaderButtonBox = styled(Box)({
  display: "flex",
  alignItems: "center",
  gap: "1.4rem",
});

export const HeaderLogoBox = styled(Box)({
  display: "flex",
  alignItems: "center",
  gap: "0.4rem",
});

export const LogoTypography = styled(Typography)({
  fontSize: "1.4rem",
  letterSpacing: "0.15rem",
  fontWeight: "500",
});

export const MainContainer = styled(Container)(({ theme }) => ({
  maxWidth: theme.breakpoints.values["lg"],
  padding: "4.8rem 0 9.6rem 0",
  margin: "0 auto",
}));

export const TwoColumnGridBox = styled(Box)({
  display: "grid",
  gridTemplateColumns: "1fr 1fr",
  columnGap: "3.2rem",
  alignItems: "center",
  justifyItems: "center",
  padding: "0 3.2rem",
});

export const AlignStartGridBox = styled(Box)({
  alignSelf: "start",
});

export const FeaturesCard = styled(Card)({
  backgroundColor: "transparent",
  boxShadow: "none",
  border: "none",
});

export const FeaturesCardContent = styled(CardContent)({
  display: "flex",
  flexDirection: "column",
  justifyContent: "flex-start",
  gap: "1.2rem",
  "& > :first-child": {
    textAlign: "center",
  },
});

export const FeaturesTypography = styled(Typography)({
  fontSize: "1.6rem",
  lineHeight: "2.2rem",
});
