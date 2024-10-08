import * as yup from "yup";

export const workoutSessionFormSchema = yup.object().shape({
  workoutSessionName: yup
    .string()
    .min(2, ({ min }) => `Requires atleast ${min} characters`)
    .required("Required"),
  date: yup.date().required("Required")
});
