import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";
import { addWorkoutSession } from "../api/WorkoutSessionApi";

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
      onSubmit={onSubmit}
    >
      {({ isSubmitting }) => (
        <Form>
          <Field
            label="Name"
            placeholder="Enter your workout session name"
            name="workoutSessionName"
            type="text"
          />
          <Field
            label="Date"
            placeholder="Enter your workout session date"
            name="date"
            type="date"
          />
          <Button variant="outlined" type="submit" disabled={isSubmitting}>
            Submit
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default WorkoutSessionForm;
