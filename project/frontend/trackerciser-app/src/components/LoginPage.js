import { Typography } from "@mui/material";
import LoginForm from "../forms/LoginForm";
import {
  MainContainer,
  TextAlignCenterBoxMainColor,
} from "../styles/StyledComponents";

function LoginPage() {
  return (
    <MainContainer>
      <TextAlignCenterBoxMainColor>
        <Typography variant="h3">LOGIN INTO YOUR ACCOUNT</Typography>
      </TextAlignCenterBoxMainColor>
      <LoginForm />
    </MainContainer>
  );
}

export default LoginPage;
