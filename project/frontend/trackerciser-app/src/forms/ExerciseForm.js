import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import { useEffect, useState } from "react";
import { retrieveAllExerciseTypes } from "../api/ExerciseTypeApi";
import { addExerciseToWorkoutSession } from "../api/ExerciseApi";
import { ExerciseFormBox, StyledFormSelect } from "../styles/StyledComponents";
import { MenuItem } from "@mui/material";

function ExerciseForm({ workoutSessionId, onExerciseAdded }) {
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
      //   validationSchema={workoutSessionFormSchema}
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
            <Button type="submit" disabled={isSubmitting}>
              Submit
            </Button>
          </ExerciseFormBox>
        </Form>
      )}
    </Formik>
  );
}

export default ExerciseForm;
