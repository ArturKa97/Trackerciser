import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";

function SetsRepsForm({ exerciseInfo, onFormClose, isAddingNew }) {
  return (
    <Formik
      initialValues={{
        sets: exerciseInfo?.sets || "",
        reps: exerciseInfo?.reps || "",
        weight: exerciseInfo?.weight || "",
        rest: exerciseInfo?.rest || "",
      }}
      //   validationSchema={workoutSessionFormSchema}
      onSubmit={(values) => {
        if (isAddingNew) {
          console.log("Adding new exerciseInfo:", values);
        } else {
          console.log("Editing exerciseInfo:", values);
        }
        onFormClose();
      }}
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <Field label="Sets" placeholder="Set" name="sets" type="text" />
          <Field
            label="Reps"
            placeholder="Repetitions"
            name="reps"
            type="text"
          />
          <Field
            label="Weight"
            placeholder="Weight"
            name="weight"
            type="text"
          />
          <Field
            label="Rest"
            placeholder="Rest between sets"
            name="rest"
            type="text"
          />

          <Button variant="outlined" type="submit" disabled={isSubmitting}>
            {isAddingNew ? "Add" : "Update"}
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default SetsRepsForm;
