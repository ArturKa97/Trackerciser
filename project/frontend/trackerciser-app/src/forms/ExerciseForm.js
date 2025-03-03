import { Field, Form, Formik } from "formik";
import { useEffect, useState } from "react";
import { retrieveAllExerciseTypes } from "../api/ExerciseTypeApi";
import { addExerciseToWorkoutSession } from "../api/ExerciseApi";
import {
  AddActionButton,
  DeleteOrCloseActionButton,
  ExerciseFormBox,
  FormActionButtonBox,
  StyledFormSelect,
} from "../styles/StyledComponents";
import { MenuItem } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import ClearIcon from "@mui/icons-material/Clear";
import { useSelector } from "react-redux";
import { selectUserDTO } from "../store/slices/userSlice";

function ExerciseForm({ workoutSessionId, onExerciseAdded, onFormClose }) {
  const [exerciseTypes, setExerciseTypes] = useState([]);
  const userId = useSelector(selectUserDTO)?.id;
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
    addExerciseToWorkoutSession(workoutSessionId, exerciseId, userId)
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
            <FormActionButtonBox>
              <AddActionButton type="submit" disabled={isSubmitting}>
                <AddIcon />
              </AddActionButton>
              <DeleteOrCloseActionButton onClick={onFormClose}>
                <ClearIcon />
              </DeleteOrCloseActionButton>
            </FormActionButtonBox>
          </ExerciseFormBox>
        </Form>
      )}
    </Formik>
  );
}

export default ExerciseForm;
