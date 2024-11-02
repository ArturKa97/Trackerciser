import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import {
  addExerciseInfoToExercise,
  updateExerciseInfoById,
} from "../api/SetsRepsApi";
import { setsRepsFormSchema } from "../schemas";
import Box from "@mui/material/Box";
import "../styles/form.css";

function SetsRepsForm({ exerciseId, exerciseInfo, onFormClose, isAddingNew }) {
  const onSubmit = async (values) => {
    try {
      if (isAddingNew) {
        await addExerciseInfoToExerciseCall(exerciseId, values);
      } else {
        await updateExerciseInfoByIdCall(exerciseInfo.id, values);
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

  const updateExerciseInfoByIdCall = (exerciseInfoId, values) => {
    return updateExerciseInfoById(exerciseInfoId, values)
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
      validationSchema={setsRepsFormSchema}
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

export default SetsRepsForm;
