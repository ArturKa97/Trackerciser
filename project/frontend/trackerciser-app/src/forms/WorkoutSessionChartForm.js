import { Field, Form, Formik } from "formik";
import { RowFlexBox, SmallFormSubmitButton } from "../styles/StyledComponents";
import { TextField } from "@mui/material";
import { chartFormSchema } from "../schemas";

function WorkoutSessionChartForm({ retrieveWorkoutSessions }) {
  const onSubmit = async (values) => {
    try {
      await retrieveWorkoutSessions(values.fromDate, values.toDate);
    } catch (error) {
      console.log(error);
    }
  };

  const currentToDate = new Date();
  const priorMonthDate = new Date();
  priorMonthDate.setMonth(currentToDate.getMonth() - 1);

  return (
    <Formik
      initialValues={{
        fromDate: priorMonthDate.toLocaleDateString("en-CA"),
        toDate: currentToDate.toLocaleDateString("en-CA"),
      }}
      onSubmit={onSubmit}
      validationSchema={chartFormSchema}
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <RowFlexBox>
            <Field
              label="From:"
              as={TextField}
              error={touched.fromDate && !!errors.fromDate}
              helperText={touched.fromDate && errors.fromDate}
              placeholder="From"
              name="fromDate"
              type="date"
            />
            <Field
              label="To:"
              as={TextField}
              error={touched.toDate && !!errors.toDate}
              helperText={touched.toDate && errors.toDate}
              placeholder="To"
              name="toDate"
              type="date"
            />
            <SmallFormSubmitButton type="submit" disabled={isSubmitting}>
              SUBMIT
            </SmallFormSubmitButton>
          </RowFlexBox>
        </Form>
      )}
    </Formik>
  );
}

export default WorkoutSessionChartForm;
