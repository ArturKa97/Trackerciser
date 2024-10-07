import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import { addWorkoutSession } from "../api/WorkoutSessionApi";

const onSubmit = (values, actions) => {
  addNewWorkoutSession(values);
console.log(values);
};

const addNewWorkoutSession = (values) => {
    addWorkoutSession(values)
    .then((response) => console.log(response))
    .catch((error) => console.log(error))
    .finally(() => console.log("cleanup"));
}

function WorkoutSessionForm() {
  return (
    <Formik
      initialValues={{ workoutSessionName: "asasa", date: "2022-02-02" }}
      onSubmit={onSubmit}
    >
      {({isSubmitting}) => (
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
          <Button
            variant="outlined"
            type="submit"
            disabled={isSubmitting}
          >
            Submit
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default WorkoutSessionForm;
