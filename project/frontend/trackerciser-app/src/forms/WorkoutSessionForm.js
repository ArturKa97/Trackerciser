import { Field, Form, Formik } from "formik";
import {
  addWorkoutSession,
  updateWorkoutSessionById,
} from "../api/WorkoutSessionApi";
import { workoutSessionFormSchema } from "../schemas";
import { TextField } from "@mui/material";
import {
  AddActionButton,
  DeleteOrCloseActionButton,
  EditActionButton,
  FormActionButtonBox,
  FormBox,
  FormTextFieldBox,
} from "../styles/StyledComponents";
import AddIcon from "@mui/icons-material/Add";
import ClearIcon from "@mui/icons-material/Clear";
import EditIcon from "@mui/icons-material/Edit";
import { useSelector } from "react-redux";
import { selectUserDTO } from "../store/slices/userSlice";

function WorkoutSessionForm({
  onSuccess,
  initialWorkoutSessionValues,
  isAddingNew,
  onClose,
}) {
  const userId = useSelector(selectUserDTO)?.id;
  const onSubmit = async (values, actions) => {
    try {
      if (isAddingNew) {
        await addWorkoutSessionCall(values, actions);
      } else {
        await updateWorkoutSessionByIdCall(
          initialWorkoutSessionValues.id,
          values,
          actions
        );
      }
    } catch (error) {
      console.log(error);
    }
  };

  const addWorkoutSessionCall = (values, actions) => {
    addWorkoutSession(values, userId)
      .then(() => {
        actions.setSubmitting(false);
        actions.resetForm();
        onSuccess();
      })
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  };
  const updateWorkoutSessionByIdCall = (workoutSessionId, values, actions) => {
    return updateWorkoutSessionById(workoutSessionId, values)
      .then(() => {
        actions.setSubmitting(false);
        actions.resetForm();
        onSuccess();
      })
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  };

  return (
    <Formik
      initialValues={{
        workoutSessionName:
          initialWorkoutSessionValues?.workoutSessionName || "",
        date: initialWorkoutSessionValues?.date || "",
      }}
      validationSchema={workoutSessionFormSchema}
      onSubmit={onSubmit}
      enableReinitialize
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <FormBox>
            <FormTextFieldBox>
              <Field
                label="Name*"
                error={
                  touched.workoutSessionName && !!errors.workoutSessionName
                }
                helperText={
                  touched.workoutSessionName && errors.workoutSessionName
                }
                as={TextField}
                placeholder="Workout session name"
                name="workoutSessionName"
                type="text"
              />

              <Field
                label="Date*"
                error={touched.date && !!errors.date}
                helperText={touched.date && errors.date}
                as={TextField}
                name="date"
                type="date"
                InputLabelProps={{ shrink: true }}
              />
            </FormTextFieldBox>
            <FormActionButtonBox>
              {isAddingNew ? (
                <AddActionButton type="submit" disabled={isSubmitting}>
                  <AddIcon />
                </AddActionButton>
              ) : (
                <EditActionButton type="submit" disabled={isSubmitting}>
                  <EditIcon />
                </EditActionButton>
              )}
              <DeleteOrCloseActionButton onClick={onClose}>
                <ClearIcon />
              </DeleteOrCloseActionButton>
            </FormActionButtonBox>
          </FormBox>
        </Form>
      )}
    </Formik>
  );
}

export default WorkoutSessionForm;
