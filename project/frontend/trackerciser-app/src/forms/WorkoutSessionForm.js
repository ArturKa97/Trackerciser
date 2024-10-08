import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";
import { addWorkoutSession } from "../api/WorkoutSessionApi";
import { workoutSessionFormSchema } from "../schemas";

function WorkoutSessionForm() {
  const navigate = useNavigate();

  const onSubmit = (values, actions) => {
    addNewWorkoutSession(values);
  };

  function navigateToCreatedWorkoutSession(id) {
    navigate("/workoutSession", { state: id });
  }

  const addNewWorkoutSession = (values) => {
    addWorkoutSession(values)
      .then((response) => navigateToCreatedWorkoutSession(response.data.id))
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  };

  return (
    <Formik
      initialValues={{ workoutSessionName: "asasa", date: "2022-02-02" }}
      validationSchema={workoutSessionFormSchema}
      onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <Field
            label="Name"
            placeholder="Enter your workout session name"
            name="workoutSessionName"
            type="text"
          />
          {touched.workoutSessionName && errors.workoutSessionName && (
            <div>{errors.workoutSessionName}</div>
          )}
          <Field
            label="Date"
            placeholder="Enter your workout session date"
            name="date"
            type="date"
          />
          {touched.date && errors.date && <div>{errors.date}</div>}
          <Button variant="outlined" type="submit" disabled={isSubmitting}>
            Submit
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default WorkoutSessionForm;
