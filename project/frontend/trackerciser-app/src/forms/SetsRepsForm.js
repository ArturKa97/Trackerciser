import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import { addExerciseInfoToExercise } from "../api/SetsRepsApi";

function SetsRepsForm({ exerciseId, exerciseInfo, onFormClose, isAddingNew }) {
  const onSubmit = async (values) => {
    try {
      if (isAddingNew) {
        await addExerciseInfoToExerciseCall(exerciseId, values);
      } else {
        console.log("Editing exerciseInfo:", values);
      }
      onFormClose();
    } catch (error) {
      console.log(error);
    }
  };

  const addExerciseInfoToExerciseCall = (exerciseId, values) => {
    return addExerciseInfoToExercise(exerciseId, values)
      .then((response) => {
        return response.data;
      })
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  };

  return (
    <Formik
      initialValues={{
        sets: exerciseInfo?.sets || "",
        reps: exerciseInfo?.reps || "",
        weight: exerciseInfo?.weight || "",
        rest: exerciseInfo?.rest || "",
      }}
      //   validationSchema={workoutSessionFormSchema}
      onSubmit={onSubmit}
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
