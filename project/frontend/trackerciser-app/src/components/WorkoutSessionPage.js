import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import * as React from "react";
import { useEffect, useState, useCallback } from "react";
import { useLocation } from "react-router-dom";
import { retrieveWorkoutSessionById } from "../api/WorkoutSessionApi";
import ExerciseTable from "./ExerciseTable";
import ExerciseForm from "../forms/ExerciseForm";
import {
  BigTableAddButton,
  MainContainer,
  WorkoutSessionPageHeaderBox,
} from "../styles/StyledComponents";
import AddIcon from "@mui/icons-material/Add";

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
    handleAddExerciseClick();
    retrieveWorkoutSessionByIdCall();
  };

  const handleAddExerciseClick = () => {
    setShowExerciseForm((prev) => !prev);
  };

  return (
    <MainContainer>
      <WorkoutSessionPageHeaderBox>
        <Typography variant="h3">{workoutSession.date}</Typography>
        <Typography variant="h3">
          {workoutSession.workoutSessionName}
        </Typography>
      </WorkoutSessionPageHeaderBox>
      <ExerciseTable
        workoutSessionExercises={workoutSession.exercisesDTO}
        workoutSessionId={workoutSession.id}
        onExerciseUpdated={handleExerciseUpdated}
        closeAddExerciseForm={handleAddExerciseClick}
      />
      {showExerciseForm && (
        <Box>
          <ExerciseForm
            workoutSessionId={workoutSession.id}
            onExerciseAdded={handleExerciseUpdated}
            onFormClose={handleAddExerciseClick}
          />
        </Box>
      )}
      <BigTableAddButton
        onClick={handleAddExerciseClick}
        disabled={showExerciseForm}
      >
        <AddIcon />
      </BigTableAddButton>
    </MainContainer>
  );
}

export default WorkoutSessionPage;
