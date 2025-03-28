import * as React from "react";
import { retrieveAllWorkoutSessionsBetweenDates } from "../api/WorkoutSessionApi";
import { useMemo, useState } from "react";
import Typography from "@mui/material/Typography";
import {
  ChartButtonGroup,
  ChartButtonGroupBox,
  ChartButtonGroupButton,
  ChartList,
  ChartListBox,
  ChartListBoxWithBorder,
  ChartListItemButton,
  MainContainer,
  ScrollableChartListBox,
  TextAlignCenterBoxLightColor,
  TextAlignCenterBoxLightColorBottomPadding,
  TextAlignCenterBoxMainColor,
  TwoColumnChartGridBox,
} from "../styles/StyledComponents";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";
import WorkoutSessionChartForm from "../forms/WorkoutSessionChartForm";
import { useMediaQuery, useTheme } from "@mui/material";
import { useSelector } from "react-redux";
import { selectUserDTO } from "../store/slices/userSlice";

function WorkoutSessionLineChart() {
  const [workoutSessionsData, setWorkoutSessionsData] = useState([]);
  const [selectedChart, setSelectedChart] = useState("Reps");
  const [selectedExercise, setSelectedExercise] = useState("");
  const [hasSubmitted, setHasSubmitted] = useState(false);
  const userId = useSelector(selectUserDTO)?.id;

  const theme = useTheme();
  const isXs = useMediaQuery(theme.breakpoints.down("sm")); // <750px
  const isXsmd = useMediaQuery(theme.breakpoints.down("xsmd")); // <900px
  const chartHeight = isXs ? 300 : isXsmd ? 400 : 600;

  const selectedExerciseChartData = useMemo(() => {
    return selectedExercise
      ? workoutSessionsData.filter(
          (session) => session.exercise === selectedExercise
        )
      : [];
  }, [selectedExercise, workoutSessionsData]);

  const retrieveWorkoutSessionsBetweenDatesCall = async (fromDate, toDate) => {
    try {
      setHasSubmitted(true);
      const response = await retrieveAllWorkoutSessionsBetweenDates(
        fromDate,
        toDate,
        userId
      );
      const formattedData = transformWorkoutData(response.data);
      setWorkoutSessionsData(formattedData);
    } catch (error) {
      console.log(error);
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
          entry[`set${set.sets}Duration`] = set.duration || 0;
        });
      });
    });

    return Array.from(dataMap.values()).sort(
      (a, b) => new Date(a.date) - new Date(b.date)
    );
  };

  const uniqueExercises = useMemo(() => {
    return [...new Set(workoutSessionsData.map((session) => session.exercise))];
  }, [workoutSessionsData]);

  const renderWorkoutChart = (valueType, label) => (
    <LineChart
      data={selectedExerciseChartData}
      margin={{ top: 30, right: 20, left: 48, bottom: 30 }}
    >
      <CartesianGrid stroke="#fafafa" strokeDasharray="0" strokeWidth={2} />
      <XAxis
        dataKey="date"
        scale="point"
        tick={{ fill: "#fafafa" }}
        tickLine={{ stroke: "#fafafa", strokeWidth: 2 }}
        tickMargin={14}
        label={{ value: "DATE", dy: 32, fill: "#fafafa" }}
      />
      <YAxis
        yAxisId="right"
        orientation="right"
        tick={{ fill: "#fafafa" }}
        tickLine={{ stroke: "#fafafa", strokeWidth: 2 }}
        tickMargin={14}
        label={{ value: label, dx: 32, fill: "#fafafa", angle: -90 }}
      />
      <Tooltip labelStyle={{ color: "black" }} />
      <Legend
        layout="horizontal"
        verticalAlign="top"
        align="center"
        wrapperStyle={{ marginTop: "-20px" }}
      />
      {selectedExerciseChartData.length > 0 &&
        Object.keys(selectedExerciseChartData[0])
          .filter((key) => key.includes(valueType))
          .map((setKey, index) => (
            <Line
              key={setKey}
              yAxisId="right"
              type="monotone"
              dataKey={setKey}
              name={`SET ${index + 1}`}
              stroke={`hsl(${index * 50}, 70%, 50%)`}
              strokeWidth={4}
              activeDot={{ r: 8 }}
              animationDuration={600}
            />
          ))}
    </LineChart>
  );

  const renderChart = () => {
    switch (selectedChart) {
      case "Reps":
        return renderWorkoutChart("Reps", "REPS");
      case "Weight":
        return renderWorkoutChart("Weight", "WEIGHT");
      case "Rest":
        return renderWorkoutChart("Rest", "REST");
      case "Duration":
        return renderWorkoutChart("Duration", "DURATION");
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
      {hasSubmitted && workoutSessionsData.length === 0 && (
        <TextAlignCenterBoxLightColorBottomPadding>
          <Typography variant="h4">
            NO WORKOUT SESSIONS FOUND IN THE SELECTED DATE RANGE.
          </Typography>
        </TextAlignCenterBoxLightColorBottomPadding>
      )}
      {workoutSessionsData && workoutSessionsData.length > 0 && (
        <>
          <TwoColumnChartGridBox>
            <ChartListBoxWithBorder>
              <ScrollableChartListBox>
                <ChartList>
                  {uniqueExercises.map((exercise, index) => (
                    <ChartListItemButton
                      key={index}
                      selected={selectedExercise === exercise}
                      onClick={() => setSelectedExercise(exercise)}
                      sx={{}}
                    >
                      {exercise}
                    </ChartListItemButton>
                  ))}
                </ChartList>
              </ScrollableChartListBox>
            </ChartListBoxWithBorder>

            <ChartListBox>
              <ChartButtonGroupBox>
                <ChartButtonGroup
                  aria-label="Chart data selector"
                  disabled={
                    !selectedExerciseChartData ||
                    selectedExerciseChartData.length === 0
                  }
                  value={selectedChart}
                  exclusive
                  onChange={(event, newValue) => {
                    if (newValue !== null) setSelectedChart(newValue);
                  }}
                >
                  <ChartButtonGroupButton
                    onClick={() => setSelectedChart("Reps")}
                    value="Reps"
                  >
                    Reps
                  </ChartButtonGroupButton>
                  <ChartButtonGroupButton
                    onClick={() => setSelectedChart("Weight")}
                    value="Weight"
                  >
                    Weight
                  </ChartButtonGroupButton>
                  <ChartButtonGroupButton
                    onClick={() => setSelectedChart("Rest")}
                    value="Rest"
                  >
                    Rest
                  </ChartButtonGroupButton>
                  <ChartButtonGroupButton
                    onClick={() => setSelectedChart("Duration")}
                    value="Duration"
                  >
                    Duration
                  </ChartButtonGroupButton>
                </ChartButtonGroup>
              </ChartButtonGroupBox>

              <ResponsiveContainer width="100%" height={chartHeight}>
                {renderChart()}
              </ResponsiveContainer>
            </ChartListBox>
          </TwoColumnChartGridBox>
        </>
      )}
    </MainContainer>
  );
}

export default WorkoutSessionLineChart;
