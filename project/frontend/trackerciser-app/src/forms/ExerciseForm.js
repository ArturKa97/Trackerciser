import { Form, Formik } from "formik";
import { useEffect, useState } from "react";
import { retrieveAllExerciseTypes } from "../api/ExerciseTypeApi";
import { addExerciseToWorkoutSession } from "../api/ExerciseApi";
import {
  AddActionButton,
  AutocompleteTextField,
  DeleteOrCloseActionButton,
  ExerciseFormBox,
  FormActionButtonBox,
  StyledAutoComplete,
} from "../styles/StyledComponents";
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
      .catch((error) => console.log(error));
  }

  const addExerciseToWorkoutSessionCall = (workoutSessionId, exerciseId) => {
    addExerciseToWorkoutSession(workoutSessionId, exerciseId, userId)
      .then(() => {
        onExerciseAdded();
      })
      .catch((error) => console.log(error));
  };

  return (
    <Formik
      initialValues={{ exerciseId: "" }}
      //   validationSchema={workoutSessionFormSchema}  // TODO: Write a schema for this form.
      onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched, values, setFieldValue }) => (
        <Form>
          <ExerciseFormBox>
            <StyledAutoComplete
              disablePortal
              options={exerciseTypes}
              getOptionLabel={(option) => option.name || ""}
              value={
                exerciseTypes.find((ex) => ex.id === values.exerciseId) || null
              }
              onChange={(event, newValue) =>
                setFieldValue("exerciseId", newValue ? newValue.id : null)
              }
              renderInput={(params) => (
                <AutocompleteTextField
                  {...params}
                  label="Choose the exercise..."
                />
              )}
            />
            <FormActionButtonBox>
              <AddActionButton
                type="submit"
                disabled={isSubmitting || !values.exerciseId}
              >
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
