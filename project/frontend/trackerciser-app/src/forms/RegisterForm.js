import { Field, Form, Formik } from "formik";
import { registerNewUser } from "../api/UserApi";
import { TextField, Typography } from "@mui/material";
import {
  ColumnFlexBox,
  FormTextFieldBoxWithLabel,
  MainFormSubmitButton,
} from "../styles/StyledComponents";
import { loginAndRegisterFormSchema } from "../schemas";
import { useNavigate } from "react-router-dom";

function RegisterForm() {
  const navigate = useNavigate();
  const onSubmit = async (values) => {
    try {
      await registerNewUserCall(values);
      navigate("/login");
    } catch (error) {
      console.log(error);
    }
  };

  function registerNewUserCall(values) {
    return registerNewUser(values)
      .then((response) => console.log(response))
      .catch((error) => console.log(error));
  }

  return (
    <Formik
      initialValues={{
        username: "",
        password: "",
      }}
      validationSchema={loginAndRegisterFormSchema}
      onSubmit={onSubmit}
      enableReinitialize
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <ColumnFlexBox>
            <FormTextFieldBoxWithLabel>
              <Typography variant="h6">USERNAME:</Typography>
              <Field
                label="Username*"
                as={TextField}
                error={touched.username && !!errors.username}
                helperText={touched.username && errors.username}
                placeholder="e.g., Myusername66"
                name="username"
                type="text"
              />
            </FormTextFieldBoxWithLabel>
            <FormTextFieldBoxWithLabel>
              <Typography variant="h6">PASSWORD:</Typography>
              <Field
                label="Password*"
                as={TextField}
                error={touched.password && !!errors.password}
                helperText={touched.password && errors.password}
                placeholder="Password"
                name="password"
                type="password"
              />
            </FormTextFieldBoxWithLabel>
            <MainFormSubmitButton type="submit" disabled={isSubmitting}>
              SIGN UP
            </MainFormSubmitButton>
          </ColumnFlexBox>
        </Form>
      )}
    </Formik>
  );
}

export default RegisterForm;
