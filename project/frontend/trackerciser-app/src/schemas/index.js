import * as yup from "yup";

export const workoutSessionFormSchema = yup.object().shape({
  workoutSessionName: yup
    .string()
    .min(2, ({ min }) => `Requires atleast ${min} characters`)
    .required("Required"),
  date: yup.date().required("Required"),
});

export const exerciseSetFormSchema = yup.object().shape({
  sets: yup
    .number("Value must be a number")
    .min(1, ({ min }) => `Value cannot be less than ${min}`)
    .max(999, ({ max }) => `Maximum value is ${max}`)
    .positive("Value must be positive")
    .integer("Must be an integer")
    .required("Required field"),
  reps: yup
    .number("Value must be a number")
    .min(1, ({ min }) => `Value cannot be less than ${min}`)
    .max(999, ({ max }) => `Maximum value is ${max}`)
    .positive("Value must be positive")
    .integer("Must be an integer")
    .required("Required field"),
  weight: yup
    .number("Value must be a number")
    .min(0.1, ({ min }) => `Value cannot be less than ${min}`)
    .max(9999, ({ max }) => `Maximum value is ${max}`)
    .positive("Value must be positive")
    .required("Required field"),
  rest: yup
    .number("Value must be a number")
    .min(1, ({ min }) => `Value cannot be less than ${min}`)
    .max(999, ({ max }) => `Maximum value is ${max}`)
    .positive("Value must be positive")
    .integer("Must be an integer")
    .required("Required field"),
});
