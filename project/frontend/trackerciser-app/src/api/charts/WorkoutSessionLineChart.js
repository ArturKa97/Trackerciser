import * as React from "react";
import { retrieveAllWorkoutSessionsBetweenDates } from "../WorkoutSessionApi";
import { useEffect, useState } from "react";
import { Field, Form, Formik } from "formik";
import Button from "@mui/material/Button";
import ButtonGroup from "@mui/material/ButtonGroup";
import Typography from "@mui/material/Typography";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import Box from "@mui/material/Box";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";

function WorkoutSessionLineChart() {
  const [workoutSessionsData, setWorkoutSessionsData] = useState([]);
  const [selectedChart, setSelectedChart] = useState("Reps");
  const [selectedExercise, setSelectedExercise] = useState("");
  const [selectedExerciseChartData, setSelectedExerciseChartData] = useState(
    []
  );
  const handleChange = (event) => {
    setSelectedExercise(event.target.value);
  };

  const onSubmit = async (values) => {
    try {
      await retrieveWorkoutSessionsBetweenDatesCall(
        values.fromDate,
        values.toDate
      );
    } catch (error) {
      console.log(error);
    } finally {
      console.log("WS between dates finally block");
    }
  };

  useEffect(() => {
    if (selectedExercise) {
      const filteredData = workoutSessionsData.filter(
        (session) => session.exercise === selectedExercise
      );
      setSelectedExerciseChartData(filteredData);
    }
  }, [selectedExercise, workoutSessionsData]);

  const retrieveWorkoutSessionsBetweenDatesCall = (fromDate, toDate) => {
    return retrieveAllWorkoutSessionsBetweenDates(fromDate, toDate)
      .then((response) => {
        const compressedData = compressData(response.data);
        const formatedChartData = formatChartData(compressedData);
        setWorkoutSessionsData(formatedChartData);
      })
      .catch((error) => console.log(error))
      .finally(() => console.log("passed"));
  };

  const compressData = (data) => {
    return data.flatMap((workoutSession) =>
      workoutSession.exercisesDTO.flatMap((exercise) =>
        exercise.exerciseSetsDTO.map((set) => ({
          date: new Date(workoutSession.date).toISOString().split("T")[0],
          exercise: exercise.exerciseTypeDTO.name || "",
          set: set.sets || 0,
          reps: set.reps || 0,
          weight: set.weight || 0,
          rest: set.rest || 0,
        }))
      )
    );
  };

  const formatChartData = (compressedData) => {
    return compressedData.reduce((acc, curr) => {
      const existingEntry = acc.find(
        (entry) => entry.date === curr.date && entry.exercise === curr.exercise
      );
      if (!existingEntry) {
        acc.push({
          date: curr.date,
          exercise: curr.exercise,
          [`set${curr.set}Reps`]: curr.reps,
          [`set${curr.set}Weight`]: curr.weight,
          [`set${curr.set}Rest`]: curr.rest,
        });
      } else {
        existingEntry[`set${curr.set}Reps`] = curr.reps;
        existingEntry[`set${curr.set}Weight`] = curr.weight;
        existingEntry[`set${curr.set}Rest`] = curr.rest;
      }
      return acc;
    }, []);
  };

  const renderChart = () => {
    switch (selectedChart) {
      case "Reps":
        return (
          <LineChart
            width={1000}
            height={400}
            data={selectedExerciseChartData}
            margin={{
              top: 5,
              right: 30,
              left: 20,
              bottom: 5,
            }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis
              dataKey="date"
              scale="point"
              label={{ value: "Date", position: "insideBottom", offset: -5 }}
            />
            <YAxis
              yAxisId="right"
              orientation="right"
              label={{ value: "Reps", angle: -90 }}
            />
            <Tooltip />
            <Legend />
            {selectedExerciseChartData.length > 0 &&
              Object.keys(selectedExerciseChartData[0])
                .filter((key) => key.includes("Reps"))
                .map((setKey, index) => (
                  <Line
                    key={setKey}
                    yAxisId="right"
                    type="monotone"
                    dataKey={setKey}
                    name={`Set ${index + 1}`}
                    stroke={`hsl(${index * 50}, 70%, 50%)`}
                    activeDot={{ r: 8 }}
                  />
                ))}
          </LineChart>
        );
      case "Weight":
        return (
          <LineChart
            width={1000}
            height={400}
            data={selectedExerciseChartData}
            margin={{
              top: 5,
              right: 30,
              left: 20,
              bottom: 5,
            }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis
              dataKey="date"
              scale="point"
              label={{ value: "Date", position: "insideBottom", offset: -5 }}
            />
            <YAxis
              yAxisId="right"
              orientation="right"
              label={{ value: "Weight", angle: -90 }}
            />
            <Tooltip />
            <Legend />
            {selectedExerciseChartData.length > 0 &&
              Object.keys(selectedExerciseChartData[0])
                .filter((key) => key.includes("Weight"))
                .map((setKey, index) => (
                  <Line
                    key={setKey}
                    yAxisId="right"
                    type="monotone"
                    dataKey={setKey}
                    name={`Set ${index + 1}`}
                    stroke={`hsl(${index * 50}, 70%, 50%)`}
                    activeDot={{ r: 8 }}
                  />
                ))}
          </LineChart>
        );
      case "Rest":
        return (
          <LineChart
            width={1000}
            height={400}
            data={selectedExerciseChartData}
            margin={{
              top: 5,
              right: 30,
              left: 20,
              bottom: 5,
            }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis
              dataKey="date"
              scale="point"
              label={{ value: "Date", position: "insideBottom", offset: -5 }}
            />
            <YAxis
              yAxisId="right"
              orientation="right"
              label={{ value: "Rest", angle: -90 }}
            />
            <Tooltip />
            <Legend />
            {selectedExerciseChartData.length > 0 &&
              Object.keys(selectedExerciseChartData[0])
                .filter((key) => key.includes("Rest"))
                .map((setKey, index) => (
                  <Line
                    key={setKey}
                    yAxisId="right"
                    type="monotone"
                    dataKey={setKey}
                    name={`Set ${index + 1}`}
                    stroke={`hsl(${index * 50}, 70%, 50%)`}
                    activeDot={{ r: 8 }}
                  />
                ))}
          </LineChart>
        );
      default:
        return null;
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        gap: 3,
        p: 3,
        backgroundColor: "#f9f9f9",
        borderRadius: "12px",
        boxShadow: "0 4px 12px rgba(0, 0, 0, 0.1)",
      }}
    >
      <Typography variant="h4" component="h1" sx={{ textAlign: "center" }}>
        Workout Progress Charts
      </Typography>
      <Formik
        initialValues={{
          fromDate: "2024-08-08",
          toDate: "2024-09-22",
        }}
        onSubmit={onSubmit}
      >
        {({ isSubmitting, errors, touched }) => (
          <Form>
            <Field
              label="Date"
              placeholder="From"
              name="fromDate"
              type="date"
            />
            <Field label="Date" placeholder="To" name="toDate" type="date" />
            <Button variant="outlined" type="submit" disabled={isSubmitting}>
              Submit
            </Button>
          </Form>
        )}
      </Formik>
      {workoutSessionsData && workoutSessionsData.length > 0 ? (
        <FormControl
          sx={{
            width: "33%",
            display: "flex",
            justifyContent: "center",
          }}
        >
          <InputLabel id="exercise-label">Exercise</InputLabel>
          <Select
            value={selectedExercise}
            labelId="exercise-label"
            id="exercise-select"
            label="Exercise"
            onChange={handleChange}
          >
            {workoutSessionsData
              .map((session) => session.exercise)
              .filter((value, index, self) => self.indexOf(value) === index)
              .map((exercise, index) => (
                <MenuItem key={index} value={exercise}>
                  {exercise}
                </MenuItem>
              ))}
          </Select>
        </FormControl>
      ) : (
        <Typography variant="h6" component="h1" sx={{ textAlign: "center" }}>
          Select the date range and submit the request for the progress data you
          want to view
        </Typography>
      )}

      {selectedExerciseChartData && selectedExerciseChartData.length > 0 ? (
        <>
          <ButtonGroup variant="contained" aria-label="Chart selector">
            <Button
              onClick={() => setSelectedChart("Reps")}
              sx={{
                backgroundColor:
                  selectedChart === "Reps" ? "#00008B" : undefined,
              }}
            >
              Reps
            </Button>
            <Button
              onClick={() => setSelectedChart("Weight")}
              sx={{
                backgroundColor:
                  selectedChart === "Weight" ? "#00008B" : undefined,
              }}
            >
              Weight
            </Button>
            <Button
              onClick={() => setSelectedChart("Rest")}
              sx={{
                backgroundColor:
                  selectedChart === "Rest" ? "#00008B" : undefined,
              }}
            >
              Rest
            </Button>
          </ButtonGroup>
          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              backgroundColor: "#ffffff",
              borderRadius: "12px",
              boxShadow: "0 2px 8px rgba(0, 0, 0, 0.1)",
              padding: 3,
            }}
          >
            {renderChart()}
          </Box>
        </>
      ) : workoutSessionsData && workoutSessionsData.length > 0 ? (
        <Typography variant="h6" component="h1" sx={{ textAlign: "center" }}>
          Select the exercise to render the progress data you want to view
        </Typography>
      ) : null}
    </Box>
  );
}

export default WorkoutSessionLineChart;
