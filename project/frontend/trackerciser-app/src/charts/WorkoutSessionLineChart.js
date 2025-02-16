import * as React from "react";
import { retrieveAllWorkoutSessionsBetweenDates } from "../api/WorkoutSessionApi";
import { useMemo, useState } from "react";
import Button from "@mui/material/Button";
import ButtonGroup from "@mui/material/ButtonGroup";
import Typography from "@mui/material/Typography";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import Box from "@mui/material/Box";
import {
  MainContainer,
  TextAlignCenterBoxLightColor,
  TextAlignCenterBoxMainColor,
} from "../styles/StyledComponents";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";
import WorkoutSessionChartForm from "../forms/WorkoutSessionChartForm";

function WorkoutSessionLineChart() {
  const [workoutSessionsData, setWorkoutSessionsData] = useState([]);
  const [selectedChart, setSelectedChart] = useState("Reps");
  const [selectedExercise, setSelectedExercise] = useState("");

  const handleChange = (event) => {
    setSelectedExercise(event.target.value);
  };

  const selectedExerciseChartData = useMemo(() => {
    return selectedExercise
      ? workoutSessionsData.filter(
          (session) => session.exercise === selectedExercise
        )
      : [];
  }, [selectedExercise, workoutSessionsData]);

  const retrieveWorkoutSessionsBetweenDatesCall = async (fromDate, toDate) => {
    try {
      const response = await retrieveAllWorkoutSessionsBetweenDates(
        fromDate,
        toDate
      );
      const formattedData = transformWorkoutData(response.data);
      console.log(formattedData);
      setWorkoutSessionsData(formattedData);
    } catch (error) {
      console.log(error);
    } finally {
      console.log("passed");
    }
  };

  const transformWorkoutData = (data) => {
    const dataMap = new Map();

    data.forEach((workoutSession) => {
      const date = new Date(workoutSession.date).toISOString().split("T")[0];

      workoutSession.exercisesDTO.forEach((exercise) => {
        const exerciseName = exercise.exerciseTypeDTO.name || "";

        exercise.exerciseSetsDTO.forEach((set) => {
          const key = `${date}-${exerciseName}`;

          if (!dataMap.has(key)) {
            dataMap.set(key, { date, exercise: exerciseName });
          }

          const entry = dataMap.get(key);
          entry[`set${set.sets}Reps`] = set.reps || 0;
          entry[`set${set.sets}Weight`] = set.weight || 0;
          entry[`set${set.sets}Rest`] = set.rest || 0;
        });
      });
    });

    return Array.from(dataMap.values());
  };
  const uniqueExercises = useMemo(() => {
    return [...new Set(workoutSessionsData.map((session) => session.exercise))];
  }, [workoutSessionsData]);

  const renderWorkoutChart = (valueType, label) => (
    <LineChart width={800} height={400} data={selectedExerciseChartData}>
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis
        dataKey="date"
        scale="point"
        label={{ value: "Date", position: "insideBottom", offset: -5 }}
      />
      <YAxis
        yAxisId="right"
        orientation="right"
        label={{ value: label, angle: -90 }}
      />
      <Tooltip />
      <Legend />
      {selectedExerciseChartData.length > 0 &&
        Object.keys(selectedExerciseChartData[0])
          .filter((key) => key.includes(valueType))
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

  const renderChart = () => {
    switch (selectedChart) {
      case "Reps":
        return renderWorkoutChart("Reps", "Reps");
      case "Weight":
        return renderWorkoutChart("Weight", "Weight");
      case "Rest":
        return renderWorkoutChart("Rest", "Rest");
      default:
        return null;
    }
  };

  return (
    <MainContainer>
      <TextAlignCenterBoxMainColor>
        <Typography variant="h3">WORKOUT PROGRESS CHARTS</Typography>
      </TextAlignCenterBoxMainColor>
      <TextAlignCenterBoxLightColor>
        <Typography variant="h4">
          PLEASE SELECT A DATE RANGE AND SUBMIT YOUR REQUEST TO VIEW THE
          PROGRESS DATA.
        </Typography>
      </TextAlignCenterBoxLightColor>
      <WorkoutSessionChartForm
        retrieveWorkoutSessions={retrieveWorkoutSessionsBetweenDatesCall}
      />
      {workoutSessionsData && workoutSessionsData.length > 0 && (
        <Select
          value={selectedExercise}
          labelId="exercise-label"
          id="exercise-select"
          label="Exercise"
          onChange={handleChange}
        >
          {uniqueExercises.map((exercise, index) => (
            <MenuItem key={index} value={exercise}>
              {exercise}
            </MenuItem>
          ))}
        </Select>
      )}

      {selectedExerciseChartData && selectedExerciseChartData.length > 0 ? (
        <>
          <ButtonGroup variant="contained" aria-label="Chart selector">
            <Button onClick={() => setSelectedChart("Reps")}>Reps</Button>
            <Button onClick={() => setSelectedChart("Weight")}>Weight</Button>
            <Button onClick={() => setSelectedChart("Rest")}>Rest</Button>
          </ButtonGroup>
          <Box>{renderChart()}</Box>
        </>
      ) : workoutSessionsData && workoutSessionsData.length > 0 ? (
        <Typography>
          Select the exercise to render the progress data you want to view
        </Typography>
      ) : null}
    </MainContainer>
  );
}

export default WorkoutSessionLineChart;
