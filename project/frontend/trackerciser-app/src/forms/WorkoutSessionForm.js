import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import {
  addWorkoutSession,
  updateWorkoutSessionById,
} from "../api/WorkoutSessionApi";
import { workoutSessionFormSchema } from "../schemas";

function WorkoutSessionForm({
  onSuccess,
  initialWorkoutSessionValues,
  isAddingNew,
}) {
  const onSubmit = async (values, actions) => {
    try {
      if (isAddingNew) {
        await addWorkoutSessionCall(values, actions);
      } else {
        await updateWorkoutSessionByIdCall(
          initialWorkoutSessionValues.id,
          values,
          actions
        );
      }
    } catch (error) {
      console.log(error);
    }
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
  const updateWorkoutSessionByIdCall = (workoutSessionId, values, actions) => {
    return updateWorkoutSessionById(workoutSessionId, values)
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
      enableReinitialize
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
