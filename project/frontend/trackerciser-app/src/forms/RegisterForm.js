import { Field, Form, Formik } from "formik";
import { registerNewUser } from "../api/UserApi";
import { TextField, Typography } from "@mui/material";
import {
  ColumnFlexBox,
  FormTextFieldBoxWithLabel,
  MainFormSubmitButton,
} from "../styles/StyledComponents";
import { loginAndRegisterFormSchema } from "../schemas";

function RegisterForm() {
  const onSubmit = async (values) => {
    try {
      await registerNewUserCall(values);
    } catch (error) {
      console.log(error);
    } finally {
      console.log("onSubmit ending");
    }
  };

  function registerNewUserCall(values) {
    return registerNewUser(values)
      .then((response) => console.log(response))
      .catch((error) => console.log(error))
      .finally(() => console.log("register call end"));
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
                label="Username"
                as={TextField}
                error={touched.username && !!errors.username}
                helperText={touched.username && errors.username}
                placeholder="e.g., myusername66"
                name="username"
                type="text"
              />
            </FormTextFieldBoxWithLabel>
            <FormTextFieldBoxWithLabel>
              <Typography variant="h6">PASSWORD:</Typography>
              <Field
                label="Password"
                as={TextField}
                error={touched.password && !!errors.password}
                helperText={touched.password && errors.password}
                placeholder="*password validation*"
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
