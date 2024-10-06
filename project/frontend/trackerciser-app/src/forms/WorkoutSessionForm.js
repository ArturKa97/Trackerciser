import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";

const onSubmit = (values) => {
  console.log(values);
};

function WorkoutSessionForm() {
  return (
    <Formik
      initialValues={{ workoutSessionName: "", date: "" }}
      onSubmit={onSubmit}
    >
      {(props) => (
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
            type="text"
          />
          <Button
            variant="outlined"
            type="submit"
            // disabled={isSubmitting}
          >
            Submit
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default WorkoutSessionForm;
