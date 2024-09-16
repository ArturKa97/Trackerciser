import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import Divider from "@mui/material/Divider";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import * as React from "react";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { retrieveWorkoutSessionById } from "../api/WorkoutSessionApi";
import ExerciseTable from "./ExerciseTable";

function WorkoutSessionPage() {
  const [workoutSession, setWorkoutSession] = useState({});
  const location = useLocation();
  useEffect(() => retrieveWorkoutSessionByIdCall(), []);

  function retrieveWorkoutSessionByIdCall() {
    retrieveWorkoutSessionById(location.state)
      .then((response) => setWorkoutSession(response.data))
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  }
  return (
    <Card variant="outlined" sx={{ maxWidth: 900 }}>
      <Box sx={{ p: 2 }}>
        <Stack
          direction="row"
          sx={{ justifyContent: "space-between", alignItems: "center" }}
        >
          <Typography gutterBottom variant="h5" component="div">
            {workoutSession.name}
          </Typography>
          <Typography gutterBottom variant="h6" component="div">
            {workoutSession.id}
          </Typography>
        </Stack>
        <Typography variant="body2" sx={{ color: "text.secondary" }}>
          {workoutSession.date}
        </Typography>
      </Box>
      <Divider />
      <ExerciseTable workoutSessionExercises={workoutSession.exerciseSet} />
    </Card>
  );
}

export default WorkoutSessionPage;
