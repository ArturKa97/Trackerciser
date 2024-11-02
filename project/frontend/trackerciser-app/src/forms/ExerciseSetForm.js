import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import {
  addExerciseSetToExercise,
  updateExerciseSetById,
} from "../api/ExerciseSetApi";
import { exerciseSetFormSchema } from "../schemas";
import Box from "@mui/material/Box";
import "../styles/form.css";

function ExerciseSetForm({ exerciseId, exerciseSets, onFormClose, isAddingNew }) {
  const onSubmit = async (values) => {
    try {
      if (isAddingNew) {
        await addExerciseSetToExerciseCall(exerciseId, values);
      } else {
        await updateExerciseSetByIdCall(exerciseSets.id, values);
      }
      onFormClose();
    } catch (error) {
      console.log(error);
    }
  };

  const addExerciseSetToExerciseCall = (exerciseId, values) => {
    return addExerciseSetToExercise(exerciseId, values)
      .then((response) => {
        return response.data;
      })
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  };

  const updateExerciseSetByIdCall = (exerciseSetId, values) => {
    return updateExerciseSetById(exerciseSetId, values)
      .then((response) => {
        return response.data;
      })
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  };

  return (
    <Formik
      initialValues={{
        sets: exerciseSets?.sets || "",
        reps: exerciseSets?.reps || "",
        weight: exerciseSets?.weight || "",
        rest: exerciseSets?.rest || "",
      }}
      validationSchema={exerciseSetFormSchema}
      onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <Box
            display="flex"
            flexDirection="row"
            justifyContent="space-between"
            gap={1}
          >
            <Box flex={1}>
              <Field
                className="form-field"
                label="Sets"
                placeholder="Set"
                name="sets"
                type="text"
              />
              {touched.sets && errors.sets && (
                <div className="form-error">{errors.sets}</div>
              )}
            </Box>
            <Box flex={1}>
              <Field
                className="form-field"
                label="Reps"
                placeholder="Repetitions"
                name="reps"
                type="text"
              />
              {touched.reps && errors.reps && (
                <div className="form-error">{errors.reps}</div>
              )}
            </Box>
            <Box flex={1}>
              <Field
                className="form-field"
                label="Weight"
                placeholder="Weight"
                name="weight"
                type="text"
              />
              {touched.weight && errors.weight && (
                <div className="form-error">{errors.weight}</div>
              )}
            </Box>
            <Box flex={1}>
              <Field
                className="form-field"
                label="Rest"
                placeholder="Rest between sets"
                name="rest"
                type="text"
              />
              {touched.rest && errors.rest && (
                <div className="form-error">{errors.rest}</div>
              )}
            </Box>

            <Button
              variant="outlined"
              type="submit"
              disabled={isSubmitting}
              sx={{ mt: 1, alignSelf: "center" }}
            >
              {isAddingNew ? "Add" : "Update"}
            </Button>
          </Box>
        </Form>
      )}
    </Formik>
  );
}

export default ExerciseSetForm;
