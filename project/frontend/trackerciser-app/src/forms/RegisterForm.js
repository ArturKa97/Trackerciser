import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import { registerNewUser } from "../api/UserApi";

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
      //   validationSchema={workoutSessionFormSchema}
      onSubmit={onSubmit}
      enableReinitialize
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <Field
            label="Username"
            placeholder="e.g., myusername66"
            name="username"
            type="text"
          />
          {/* {touched.workoutSessionName && errors.workoutSessionName && (
            <div>{errors.workoutSessionName}</div>
          )} */}
          <Field
            label="Password"
            placeholder="*password validation*"
            name="password"
            type="password"
          />
          {/* {touched.date && errors.date && <div>{errors.date}</div>} */}
          <Button variant="outlined" type="submit" disabled={isSubmitting}>
            Sign Up
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default RegisterForm;
