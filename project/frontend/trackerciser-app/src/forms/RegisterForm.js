import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";

function RegisterForm() {
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
