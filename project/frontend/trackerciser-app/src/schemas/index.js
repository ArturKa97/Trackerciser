import * as yup from "yup";

export const workoutSessionFormSchema = yup.object().shape({
  workoutSessionName: yup
    .string()
    .min(2, ({ min }) => `Requires atleast ${min} characters`)
    .max(30, ({ max }) => `Maximum ${max} characters allowed`)
    .required("Required field"),
  date: yup
    .date()
    .required("Required field")
    .min(new Date(1900, 0, 1), "Date must be later than January 1st, 1900")
    .max(new Date(), "Date cannot be in the future"),
});

export const exerciseSetFormSchema = yup.object().shape({
  sets: yup
    .number("Value must be a number")
    .typeError("Value must be a number")
    .min(1, ({ min }) => `Value cannot be less than ${min}`)
    .max(9999, ({ max }) => `Maximum value is ${max}`)
    .positive("Value must be positive")
    .integer("Must be an integer")
    .required("Required field"),
  reps: yup
    .number("Value must be a number")
    .typeError("Value must be a number")
    .min(1, ({ min }) => `Value cannot be less than ${min}`)
    .max(9999, ({ max }) => `Maximum value is ${max}`)
    .positive("Value must be positive")
    .integer("Must be an integer")
    .required("Required field"),
  weight: yup
    .number("Value must be a number")
    .typeError("Value must be a number")
    .min(0.1, ({ min }) => `Value cannot be less than ${min}`)
    .max(9999, ({ max }) => `Maximum value is ${max}`)
    .positive("Value must be positive")
    .required("Required field"),
  rest: yup
    .number("Value must be a number")
    .typeError("Value must be a number")
    .min(1, ({ min }) => `Value cannot be less than ${min}`)
    .max(9999, ({ max }) => `Maximum value is ${max}`)
    .positive("Value must be positive")
    .integer("Must be an integer")
    .required("Required field"),
  duration: yup
    .number("Value must be a number")
    .typeError("Value must be a number")
    .min(1, ({ min }) => `Value cannot be less than ${min}`)
    .max(14400, ({ max }) => `Maximum value is ${max}`)
    .positive("Value must be positive")
    .integer("Must be an integer")
    .required("Required field"),
});

export const loginAndRegisterFormSchema = yup.object().shape({
  username: yup
    .string()
    .min(1, ({ min }) => `Requires atleast ${min} characters`)
    .max(20, ({ max }) => `Maximum ${max} characters allowed`)
    .required("Required field"),
  password: yup
    .string()
    .matches(
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()=-]).{8,64}$/,
      "Password must be between 8 and 64 characters long, and include at least one uppercase letter, one lowercase letter, one number, and one special character."
    )
    .required("Required field"),
});

export const chartFormSchema = yup.object().shape({
  fromDate: yup
    .date()
    .required("Date is required")
    .min(new Date(1900, 0, 1), "Date must be later than January 1st, 1900")
    .max(new Date(), "Date cannot be in the future"),
  toDate: yup
    .date()
    .required("Date is required")
    .min(new Date(1900, 0, 1), "Date must be later than January 1st, 1900")
    .max(new Date(), "Date cannot be in the future")
    .test("is-later", "To Date must be later than from Date", function (value) {
      const { fromDate } = this.parent;
      return value >= fromDate || !value;
    }),
});
