import { Field, Form, Formik } from "formik";
import { useEffect, useState } from "react";
import { retrieveAllExerciseTypes } from "../api/ExerciseTypeApi";
import { addExerciseToWorkoutSession } from "../api/ExerciseApi";
import {
  AddActionButton,
  DeleteOrCloseActionButton,
  ExerciseFormBox,
  StyledFormSelect,
} from "../styles/StyledComponents";
import { Box, MenuItem } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import ClearIcon from "@mui/icons-material/Clear";

function ExerciseForm({ workoutSessionId, onExerciseAdded, onFormClose }) {
  const [exerciseTypes, setExerciseTypes] = useState([]);
  useEffect(() => retrieveAllExerciseTypesCall(), []);

  const onSubmit = async ({ exerciseId }, { setSubmitting, resetForm }) => {
    try {
      await addExerciseToWorkoutSessionCall(workoutSessionId, exerciseId);
      onExerciseAdded();
      resetForm();
    } catch (error) {
      console.error(error);
    } finally {
      setSubmitting(false);
    }
  };

  function retrieveAllExerciseTypesCall() {
    retrieveAllExerciseTypes()
      .then((response) => setExerciseTypes(response.data))
      .catch((error) => console.log(error))
      .finally(() => console.log("passed"));
  }

  const addExerciseToWorkoutSessionCall = (workoutSessionId, exerciseId) => {
    addExerciseToWorkoutSession(workoutSessionId, exerciseId)
      .then(() => {
        onExerciseAdded();
      })
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  };

  return (
    <Formik
      initialValues={{ exerciseId: "" }}
      //   validationSchema={workoutSessionFormSchema}  // TODO: Write a schema for this form.
      onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched, setFieldValue }) => (
        <Form>
          <ExerciseFormBox>
            <Field
              name="exerciseId"
              as={StyledFormSelect}
              labelId="exercise-select-label"
              label="Choose the exercise"
              displayEmpty
              onChange={(event) =>
                setFieldValue("exerciseId", event.target.value)
              }
            >
              <MenuItem value="" disabled>
                Choose the exercise...
              </MenuItem>
              {exerciseTypes.map((exerciseType) => (
                <MenuItem key={exerciseType.id} value={exerciseType.id}>
                  {exerciseType.name}
                </MenuItem>
              ))}
            </Field>
            <Box>
              <AddActionButton type="submit" disabled={isSubmitting}>
                <AddIcon />
              </AddActionButton>
              <DeleteOrCloseActionButton onClick={onFormClose}>
                <ClearIcon />
              </DeleteOrCloseActionButton>
            </Box>
          </ExerciseFormBox>
        </Form>
      )}
    </Formik>
  );
}

export default ExerciseForm;
