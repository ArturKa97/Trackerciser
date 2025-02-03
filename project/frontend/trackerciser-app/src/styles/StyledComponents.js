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
  IconButton,
  List,
  ListItem,
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
  columnGap: "4.8rem",
  alignItems: "center",
  justifyItems: "center",
  padding: "0 3.2rem",
});

export const AlignStartGridBox = styled(Box)({
  alignSelf: "start",
  position: "relative",
});

const slideAnimation = {
  "@keyframes slide-in-next": {
    "0%": { transform: "translateX(100%)", opacity: 0 },
    "100%": { transform: "translateX(0)", opacity: 1 },
  },
  "@keyframes slide-in-prev": {
    "0%": { transform: "translateX(-100%)", opacity: 0 },
    "100%": { transform: "translateX(0)", opacity: 1 },
  },
};

export const FeaturesCard = styled(Card)({
  backgroundColor: "transparent",
  boxShadow: "none",
  border: "none",
  height: "38rem",
});

export const FeaturesCardContent = styled(CardContent)(({ direction }) => ({
  ...slideAnimation,
  display: "flex",
  flexDirection: "column",
  justifyContent: "flex-start",
  gap: "1.2rem",
  textAlign: "center",
  animation:
    direction === 1
      ? "slide-in-next 0.3s ease-in"
      : "slide-in-prev 0.3s ease-in",
}));

export const FeaturesTypography = styled(Typography)({
  fontSize: "1.6rem",
  lineHeight: "2.2rem",
});

export const PrevIconButton = styled(IconButton)({
  backgroundColor: "white",
  position: "absolute",
  left: 0,
  top: "50%",
  transform: "translate(-100%, -50%)",
});
export const NextIconButton = styled(IconButton)({
  backgroundColor: "white",
  position: "absolute",
  right: 0,
  top: "50%",
  transform: "translate(0, -50%)",
});

export const FeaturesList = styled(List)({
  listStyleType: "disc",
  padding: "0 1.6rem",
});

export const FeaturesListItem = styled(ListItem)({
  display: "list-item",
  fontSize: "1.6rem",
  lineHeight: "2.0rem",
});
