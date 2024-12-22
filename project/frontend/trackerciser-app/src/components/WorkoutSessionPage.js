import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import Divider from "@mui/material/Divider";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import * as React from "react";
import { useEffect, useState, useCallback } from "react";
import { useLocation } from "react-router-dom";
import { retrieveWorkoutSessionById } from "../api/WorkoutSessionApi";
import ExerciseTable from "./ExerciseTable";
import { Button } from "@mui/material";
import ExerciseForm from "../forms/ExerciseForm";

function WorkoutSessionPage() {
  const [workoutSession, setWorkoutSession] = useState({});
  const [showExerciseForm, setShowExerciseForm] = useState(false);
  const location = useLocation();

  const retrieveWorkoutSessionByIdCall = useCallback(() => {
    retrieveWorkoutSessionById(location.state)
      .then((response) => setWorkoutSession(response.data))
      .catch((error) => console.log(error));
  }, [location.state]);

  useEffect(
    () => retrieveWorkoutSessionByIdCall(),
    [retrieveWorkoutSessionByIdCall]
  );

  const handleExerciseUpdated = () => {
    retrieveWorkoutSessionByIdCall();
  };

  const handleAddExerciseClick = () => {
    setShowExerciseForm((prev) => !prev);
  };

  return (
    <Card variant="outlined" sx={{ maxWidth: 900 }}>
      <Box sx={{ p: 2 }}>
        <Stack
          direction="row"
          sx={{ justifyContent: "space-between", alignItems: "center" }}
        >
          <Typography gutterBottom variant="h5" component="div">
            {workoutSession.workoutSessionName}
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
      <ExerciseTable
        workoutSessionExercises={workoutSession.exercisesDTO}
        workoutSessionId={workoutSession.id}
        onExerciseUpdated={handleExerciseUpdated}
        closeAddExerciseForm={handleAddExerciseClick}
      />
      {showExerciseForm && (
        <Box sx={{ mt: 2 }}>
          <ExerciseForm
            workoutSessionId={workoutSession.id}
            onExerciseAdded={handleExerciseUpdated}
          />
        </Box>
      )}
      <Button
        onClick={handleAddExerciseClick}
        sx={{ backgroundColor: "#212121" }}
      >
        {showExerciseForm ? "Cancel" : "Add Exercise"}
      </Button>
    </Card>
  );
}

export default WorkoutSessionPage;
