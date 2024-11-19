import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";

function LoginForm() {
  //   const onSubmit

  return (
    <Formik
    initialValues={{
        username: "",
        password: "",
      }}
      //   validationSchema={workoutSessionFormSchema}
      //   onSubmit={onSubmit}
      enableReinitialize
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <Field
            label="Username"
            placeholder="Username"
            name="username"
            type="text"
          />
          {/* {touched.workoutSessionName && errors.workoutSessionName && (
            <div>{errors.workoutSessionName}</div>
          )} */}
          <Field
            label="Password"
            placeholder="Password"
            name="password"
            type="password"
          />
          {/* {touched.date && errors.date && <div>{errors.date}</div>} */}
          <Button variant="outlined" type="submit" disabled={isSubmitting}>
            Login
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default LoginForm;
