import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import { addWorkoutSession } from "../api/WorkoutSessionApi";
import { workoutSessionFormSchema } from "../schemas";

function WorkoutSessionForm({
  onSuccess,
  initialWorkoutSessionValues,
  isAddingNew,
}) {
  const onSubmit = (values, actions) => {
    addWorkoutSessionCall(values, actions);
  };

  const addWorkoutSessionCall = (values, actions) => {
    addWorkoutSession(values)
      .then(() => {
        actions.setSubmitting(false);
        actions.resetForm();
        onSuccess();
      })
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  };

  return (
    <Formik
      initialValues={{
        workoutSessionName:
          initialWorkoutSessionValues?.workoutSessionName || "",
        date: initialWorkoutSessionValues?.date || "",
      }}
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
            {isAddingNew ? "Submit" : "Edit"}
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default WorkoutSessionForm;
