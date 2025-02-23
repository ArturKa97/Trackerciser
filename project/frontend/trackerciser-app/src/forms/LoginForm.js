import { Field, Form, Formik } from "formik";
import { useDispatch } from "react-redux";
import { userLoggedIn } from "../store/slices/userSlice";
import { loginAndAuthenticate } from "../api/UserApi";
import { useNavigate } from "react-router-dom";
import {
  ColumnFlexBox,
  FormTextFieldBoxWithLabel,
  MainFormSubmitButton,
} from "../styles/StyledComponents";
import { TextField, Typography } from "@mui/material";
import { loginAndRegisterFormSchema } from "../schemas";

function LoginForm() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const onSubmit = async (values) => {
    try {
      await LoginAndAuthenticateCall(values);
      navigate("/");
    } catch (error) {
      console.log(error);
    } finally {
      console.log("onSubmit ending");
    }
  };

  function LoginAndAuthenticateCall(values) {
    return loginAndAuthenticate(values)
      .then((response) => dispatch(userLoggedIn(response)))
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
                placeholder="Username"
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
                placeholder="Password"
                name="password"
                type="password"
              />
            </FormTextFieldBoxWithLabel>
            <MainFormSubmitButton type="submit" disabled={isSubmitting}>
              LOGIN
            </MainFormSubmitButton>
          </ColumnFlexBox>
        </Form>
      )}
    </Formik>
  );
}

export default LoginForm;
