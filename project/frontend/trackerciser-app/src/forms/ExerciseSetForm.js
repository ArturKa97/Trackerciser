import { Field, Form, Formik } from "formik";
import {
  addExerciseSetToExercise,
  updateExerciseSetById,
} from "../api/ExerciseSetApi";
import { exerciseSetFormSchema } from "../schemas";
import Box from "@mui/material/Box";
import "../styles/form.css";
import { TextField } from "@mui/material";
import {
  AddActionButton,
  DeleteOrCloseActionButton,
  EditActionButton,
  FormBox,
  FormTextFieldBox,
} from "../styles/StyledComponents";
import AddIcon from "@mui/icons-material/Add";
import EditIcon from "@mui/icons-material/Edit";
import ClearIcon from "@mui/icons-material/Clear";

function ExerciseSetForm({
  exerciseId,
  exerciseSets,
  onFormClose,
  isAddingNew,
}) {
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

  // TODO: Integrate the newly added duration field
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
          <FormBox>
            <FormTextFieldBox>
              <Field
                label="Sets *"
                error={touched.sets && !!errors.sets}
                helperText={touched.sets && errors.sets}
                as={TextField}
                placeholder="Set"
                name="sets"
                type="text"
              />

              <Field
                label="Reps *"
                error={touched.reps && !!errors.reps}
                helperText={touched.reps && errors.reps}
                as={TextField}
                placeholder="Repetitions"
                name="reps"
                type="text"
              />

              <Field
                label="Weight *"
                error={touched.weight && !!errors.weight}
                helperText={touched.weight && errors.weight}
                as={TextField}
                placeholder="Weight"
                name="weight"
                type="text"
              />

              <Field
                label="Rest *"
                error={touched.rest && !!errors.rest}
                helperText={touched.rest && errors.rest}
                as={TextField}
                placeholder="Rest between sets"
                name="rest"
                type="text"
              />
            </FormTextFieldBox>

            <Box>
              {isAddingNew ? (
                <AddActionButton type="submit" disabled={isSubmitting}>
                  <AddIcon />
                </AddActionButton>
              ) : (
                <EditActionButton type="submit" disabled={isSubmitting}>
                  <EditIcon />
                </EditActionButton>
              )}
              <DeleteOrCloseActionButton onClick={onFormClose}>
                <ClearIcon />
              </DeleteOrCloseActionButton>
            </Box>
          </FormBox>
        </Form>
      )}
    </Formik>
  );
}

export default ExerciseSetForm;
