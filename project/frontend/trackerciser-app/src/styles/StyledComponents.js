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
  TableCell,
  ListItemButton,
  ToggleButton,
  ToggleButtonGroup,
  Autocomplete,
  TextField,
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

export const HeaderHomeButton = styled(Button)(({ theme }) => ({
  color: theme.palette.primary.contrastText,
  transition: "background-color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: theme.palette.primary.light,
  },
}));

export const HeaderMenuButton = styled(IconButton)(({ theme }) => ({
  color: theme.palette.primary.contrastText,
  transition: "background-color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: theme.palette.primary.light,
  },
}));

export const FlexToolbar = styled(Toolbar)({
  display: "flex",
  justifyContent: "space-between",
  alignContent: "center",
});

export const HeaderButtonBox = styled(Box)(({ theme }) => ({
  display: "flex",
  alignItems: "center",
  gap: "1.4rem",
  [theme.breakpoints.down("xsmd")]: {
    gap: "1.2rem",
  },
  [theme.breakpoints.down("sm")]: {
    gap: "0.8rem",
  },
  [theme.breakpoints.down("xs")]: {
    gap: "0.4rem",
  },
  [theme.breakpoints.down("xss")]: {
    gap: "0.2rem",
  },
}));

export const HeaderLogoBox = styled(Box)({
  display: "flex",
  alignItems: "center",
  gap: "0.4rem",
});

export const HeaderMenuBoxSm = styled(Box)({
  display: "flex",
  alignItems: "center",
  justifyContent: "space-between",
  width: "100%",
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
  [theme.breakpoints.down("md")]: {
    padding: "4.8rem 1.4rem 8.0rem 1.4rem",
  },
}));

export const TwoColumnGridBox = styled(Box)(({ theme }) => ({
  display: "grid",
  gridTemplateColumns: "0.8fr 1.2fr",
  columnGap: "4.8rem",
  alignItems: "center",
  justifyItems: "center",
  padding: "0 3.2rem",
  [theme.breakpoints.down("xsmd")]: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    gap: "3.2rem",
    textAlign: "center",
  },
}));

export const TwoColumnChartGridBox = styled(Box)(({ theme }) => ({
  backgroundColor: theme.palette.primary.light,
  borderTop: "2px solid #fafafa",
  display: "grid",
  gridTemplateColumns: "0.4fr 1.6fr",
  columnGap: "3.2rem",
  alignItems: "start",
  justifyItems: "center",
  padding: "2.4rem 1.2rem",
  minHeight: "30rem",
  [theme.breakpoints.down("smd")]: {
    gap: "2.4rem",
    padding: "2.4rem 0.8rem 2.4rem 1.2rem",
  },
  [theme.breakpoints.down("xsmd")]: {
    gap: "1.6rem",
    padding: "2.4rem 0.4rem 2.4rem 0.8rem",
  },
  [theme.breakpoints.down("sm")]: {
    gap: "0.8rem",
    padding: "2.4rem 0.4rem 2.4rem 1.2rem",
  },
}));

export const AlignStartGridBox = styled(Box)(({ theme }) => ({
  alignSelf: "start",
  position: "relative",
  paddingBottom: "4.8rem",
  [theme.breakpoints.down("xsmd")]: {
    width: "100%",
  },
}));

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

export const FeaturesCard = styled(Card)(({ theme }) => ({
  backgroundColor: "transparent",
  boxShadow: "none",
  border: "none",
  height: "38rem",
  [theme.breakpoints.down("xsmd")]: {
    minHeight: "26rem",
    maxHeight: "34rem",
    height: "auto",
    width: "100%",
  },
}));

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
  [theme.breakpoints.down("xsmd")]: {
    top: "40%",
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
  [theme.breakpoints.down("xsmd")]: {
    top: "40%",
  },
}));

export const FeaturesList = styled(List)({
  listStyleType: "disc",
  padding: "0 1.6rem",
});

export const FeaturesListItem = styled(ListItem)(({ theme }) => ({
  display: "list-item",
  fontSize: "1.6rem",
  lineHeight: "2.0rem",
  [theme.breakpoints.down("md")]: {
    fontSize: "1.4rem",
  },
}));

export const FlexRadioGroupBox = styled(Box)(({ theme }) => ({
  display: "flex",
  justifyContent: "center",
  alignItems: "flex-end",
  [theme.breakpoints.down("xsmd")]: {
    alignItems: "center",
  },
}));

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
  "&:disabled": {
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
  "&:disabled": {
    color: theme.palette.primary.contrastText,
    cursor: "not-allowed",
    opacity: 0.5,
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

export const ColumnFlexBox = styled(Box)(({ theme }) => ({
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "center",
  gap: "2.4rem",
  backgroundColor: theme.palette.primary.light,
  padding: "3.2rem 3.2rem",
}));

export const RowFlexBox = styled(Box)(({ theme }) => ({
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  gap: "2.4rem",
  backgroundColor: theme.palette.primary.light,
  padding: "3.2rem 3.2rem",
}));

export const TextAlignCenterBox = styled(Box)({
  textAlign: "center",
});

export const TextAlignCenterBoxMainColor = styled(Box)(({ theme }) => ({
  textAlign: "center",
  backgroundColor: theme.palette.primary.main,
  padding: "3.2rem 3.2rem",
  borderBottom: "2px solid #fafafa",
}));
export const TextAlignCenterBoxLightColor = styled(Box)(({ theme }) => ({
  textAlign: "center",
  backgroundColor: theme.palette.primary.light,
  padding: "2.4rem 3.2rem 0 3.2rem",
}));
export const TextAlignCenterBoxLightColorBottomPadding = styled(Box)(
  ({ theme }) => ({
    textAlign: "center",
    color: theme.palette.secondary.main,
    backgroundColor: theme.palette.primary.light,
    padding: "0 3.2rem 2.4rem 3.2rem",
  })
);

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

export const FormActionButtonBox = styled(Box)({
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
});

export const StyledFormSelect = styled(Select)({
  width: "50%",
});

export const FourtyWidthTableCell = styled(TableCell)({
  width: "40%",
});
export const TwentyWidthTableCell = styled(TableCell)({
  width: "20%",
});
export const TenWidthTableCell = styled(TableCell)({
  width: "10%",
});
export const TwentyRightAlignWidthTableCell = styled(TableCell)({
  width: "20%",
  textAlign: "right",
});
export const RightAlingTableCell = styled(TableCell)({
  textAlign: "right",
});

export const MainFormSubmitButton = styled(Button)(({ theme }) => ({
  backgroundColor: "#1976d2",
  color: theme.palette.primary.contrastText,
  width: "20%",
  padding: "0.8rem 1.2rem",
  fontSize: "1.2rem",
  transition: "background-color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: theme.palette.primary.main,
  },
  "&:disabled": {
    backgroundColor: theme.palette.primary.main,
    color: theme.palette.primary.contrastText,
    cursor: "not-allowed",
    opacity: 0.5,
  },
}));

export const SmallFormSubmitButton = styled(Button)(({ theme }) => ({
  backgroundColor: "#1976d2",
  color: theme.palette.primary.contrastText,
  width: "15%",
  padding: "0.8rem 1.2rem",
  fontSize: "1.0rem",
  transition: "background-color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: theme.palette.primary.main,
  },
  "&:disabled": {
    backgroundColor: theme.palette.primary.main,
    color: theme.palette.primary.contrastText,
    cursor: "not-allowed",
    opacity: 0.5,
  },
}));

export const ChartListBox = styled(Box)(({ theme }) => ({
  height: "100%",
  width: "100%",
  backgroundColor: theme.palette.primary.light,
}));
export const ChartListBoxWithBorder = styled(Box)(({ theme }) => ({
  height: "100%",
  width: "100%",
  border: "2px solid #fafafa",
  backgroundColor: theme.palette.primary.light,
}));

export const ScrollableChartListBox = styled(Box)({
  maxHeight: "44.0rem",
  overflowY: "auto",
});
export const ChartList = styled(List)(({ theme }) => ({
  height: "100%",
  width: "100%",
  overflowY: "auto",
}));

export const ChartListItemButton = styled(ListItemButton)(({ theme }) => ({
  transition: "background-color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: theme.palette.primary.main,
  },
  "&.Mui-selected": {
    backgroundColor: theme.palette.primary.main,
  },
}));

export const ChartButtonGroupBox = styled(Box)(({ theme }) => ({
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  paddingBottom: "1.2rem",
}));

export const ChartButtonGroup = styled(ToggleButtonGroup)(({ theme }) => ({
  border: "2px solid #fafafa",
  "&:disabled": {
    cursor: "not-allowed",
    opacity: 0.5,
    borderColor: "rgba(250, 250, 250, 0.3)",
  },
}));

export const ChartButtonGroupButton = styled(ToggleButton)(({ theme }) => ({
  backgroundColor: theme.palette.primary.light,
  color: theme.palette.primary.contrastText,
  transition: "background-color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: theme.palette.primary.main,
  },
  "&.Mui-selected": {
    backgroundColor: theme.palette.primary.main,
  },
  "&:disabled": {
    backgroundColor: theme.palette.primary.main,
    color: theme.palette.primary.contrastText,
    cursor: "not-allowed",
    opacity: 0.5,
  },
}));

export const FooterBox = styled(Box)(({ theme }) => ({
  backgroundColor: theme.palette.primary.main,
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  padding: "1.4rem 1.4rem",
  position: "relative",
}));

export const FooterVersionBox = styled(Box)({
  position: "absolute",
  bottom: "0",
  right: "0.5%",
});

export const FormTextFieldBoxWithLabel = styled(Box)({
  display: "flex",
  width: "16.0rem",
  flexDirection: "column",
  gap: "0.8rem",
});

export const PaginationBox = styled(Box)({
  padding: "2.4rem 2.4rem",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
});

export const StyledAutoComplete = styled(Autocomplete)({
  width: "50%",
});

export const AutocompleteTextField = styled(TextField)(({ theme }) => ({
  color: theme.palette.primary.contrastText,
  backgroundColor: theme.palette.primary.light,
  transition: "background-color 0.3s ease-in-out",
  "&:hover": {
    backgroundColor: theme.palette.primary.main,
  },
  "&.Mui-focused": {
    backgroundColor: theme.palette.primary.main,
  },
  "&.Mui-selected": {
    backgroundColor: theme.palette.primary.main,
  },
}));
