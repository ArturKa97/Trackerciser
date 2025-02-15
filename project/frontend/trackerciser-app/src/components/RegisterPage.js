import { Typography } from "@mui/material";
import RegisterForm from "../forms/RegisterForm";
import {
  MainContainer,
  TextAlignCenterBoxMainColor,
} from "../styles/StyledComponents";

function RegisterPage() {
  return (
    <MainContainer>
      <TextAlignCenterBoxMainColor>
        <Typography variant="h3">REGISTER YOUR ACCOUNT</Typography>
      </TextAlignCenterBoxMainColor>
      <RegisterForm />
    </MainContainer>
  );
}

export default RegisterPage;
