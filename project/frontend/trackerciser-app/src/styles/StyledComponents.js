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
  Radio,
  Select,
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
  gridTemplateColumns: "0.8fr 1.2fr",
  columnGap: "4.8rem",
  alignItems: "center",
  justifyItems: "center",
  padding: "0 3.2rem",
});

export const AlignStartGridBox = styled(Box)({
  alignSelf: "start",
  position: "relative",
  paddingBottom: "4.8rem",
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

export const PrevIconButton = styled(IconButton)(({ theme }) => ({
  color: theme.palette.primary.contrastText,
  position: "absolute",
  left: 0,
  top: "50%",
  transform: "translate(-100%, -50%)",
  transition: "background-color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: theme.palette.primary.light,
  },
}));
export const NextIconButton = styled(IconButton)(({ theme }) => ({
  color: theme.palette.primary.contrastText,
  position: "absolute",
  right: 0,
  top: "50%",
  transform: "translate(0, -50%)",
  transition: "background-color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: theme.palette.primary.light,
  },
}));

export const FeaturesList = styled(List)({
  listStyleType: "disc",
  padding: "0 1.6rem",
});

export const FeaturesListItem = styled(ListItem)({
  display: "list-item",
  fontSize: "1.6rem",
  lineHeight: "2.0rem",
});

export const FlexRadioGroupBox = styled(Box)({
  display: "flex",
  justifyContent: "center",
  alignItems: "flex-end",
});

export const StyledRadioButton = styled(Radio)(({ theme }) => ({
  color: theme.palette.primary.contrastText,
  "&.Mui-checked": {
    color: theme.palette.secondary.main,
  },
}));

export const EditActionButton = styled(IconButton)(({ theme }) => ({
  color: "#1976d2",
  transition: "color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: "transparent",
    color: theme.palette.primary.contrastText,
  },
}));

export const DeleteOrCloseActionButton = styled(IconButton)(({ theme }) => ({
  color: theme.palette.secondary.main,
  transition: "color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: "transparent",
    color: theme.palette.primary.contrastText,
  },
}));

export const BigTableAddButton = styled(IconButton)(({ theme }) => ({
  color: theme.palette.primary.contrastText,
  backgroundColor: theme.palette.primary.light,
  borderRadius: "0px",
  transition: "background-color 0.3s ease-in-out",
  width: "100%",
  "&:hover": {
    backgroundColor: theme.palette.primary.main,
  },
  "&.Mui-disabled": {
    backgroundColor: theme.palette.primary.light,
    color: theme.palette.primary.contrastText,
    cursor: "not-allowed",
    opacity: 0.5,
  },
}));

export const AddActionButton = styled(IconButton)(({ theme }) => ({
  color: "#1976d2",
  transition: "color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: "transparent",
    color: theme.palette.primary.contrastText,
  },
}));

export const FormBox = styled(Box)({
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
});

export const FormTextFieldBox = styled(Box)({
  display: "flex",
  gap: "2.4rem",
  alignItems: "center",
});

export const TextAlignCenterBox = styled(Box)({
  textAlign: "center",
});

export const WorkoutSessionPageHeaderBox = styled(Box)(({ theme }) => ({
  backgroundColor: theme.palette.primary.main,
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
  padding: "2.4rem",
  gap: "1.2rem",
  borderBottom: "2px solid #fafafa",
}));

export const ExerciseFormBox = styled(Box)(({ theme }) => ({
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  padding: "1.0rem",
  backgroundColor: theme.palette.primary.main,
  borderBottom: "2px solid #fafafa",
}));

export const StyledFormSelect = styled(Select)({
  width: "50%",
});
