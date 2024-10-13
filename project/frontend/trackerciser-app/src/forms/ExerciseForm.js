import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import { useEffect, useState } from "react";
import { retrieveAllExerciseTypes } from "../api/ExerciseTypeApi";

function ExerciseForm() {
  const [exerciseTypes, setExerciseTypes] = useState([]);
  useEffect(() => retrieveAllExerciseTypesCall(), []);

  function retrieveAllExerciseTypesCall() {
    retrieveAllExerciseTypes()
      .then((response) => setExerciseTypes(response.data))
      .catch((error) => console.log(error))
      .finally(() => console.log("passed"));
  }

  return (
    <Formik
      initialValues={{ exerciseName: "" }}
      //   validationSchema={workoutSessionFormSchema}
      //   onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched, setFieldValue }) => (
        <Form>
          <FormControl fullWidth variant="outlined">
            <InputLabel id="exercise-select-label">
              Choose the exercise
            </InputLabel>
            <Field
              name="exerciseName"
              as={Select}
              labelId="exercise-select-label"
              label="Choose the exercise"
              onChange={(event) =>
                setFieldValue("exerciseName", event.target.value)
              }
            >
              {exerciseTypes.map((exerciseType) => (
                <MenuItem key={exerciseType.id} value={exerciseType.id}>
                  {exerciseType.name}
                </MenuItem>
              ))}
            </Field>
          </FormControl>
          {touched.workoutSessionName && errors.workoutSessionName && (
            <div>{errors.workoutSessionName}</div>
          )}
          <Button variant="outlined" type="submit" disabled={isSubmitting}>
            Submit
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default ExerciseForm;
