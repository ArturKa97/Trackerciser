import { Field, Form, Formik } from "formik";
import { registerNewUser } from "../api/UserApi";
import { TextField } from "@mui/material";
import {
  ColumnFlexBox,
  MainFormSubmitButton,
} from "../styles/StyledComponents";

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
    //TODO: Make a validation schema
    <Formik
      initialValues={{
        username: "",
        password: "",
      }}
      //   validationSchema={workoutSessionFormSchema}
      onSubmit={onSubmit}
      enableReinitialize
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <ColumnFlexBox>
            <Field
              label="Username"
              as={TextField}
              error={touched.username && !!errors.username}
              helperText={touched.username && errors.username}
              placeholder="e.g., myusername66"
              name="username"
              type="text"
            />
            <Field
              label="Password"
              as={TextField}
              error={touched.password && !!errors.password}
              helperText={touched.password && errors.password}
              placeholder="*password validation*"
              name="password"
              type="password"
            />
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
