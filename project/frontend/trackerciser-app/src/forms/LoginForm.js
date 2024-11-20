import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import { useDispatch } from "react-redux";
import { userLoggedIn } from "../store/slices/userSlice";
import { loginAndAuthenticate } from "../api/UserApi";
import { useNavigate } from "react-router-dom";

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
      //   validationSchema={workoutSessionFormSchema}
      onSubmit={onSubmit}
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
