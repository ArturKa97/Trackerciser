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
import { useSelector } from "react-redux";
import { selectUserDTO } from "../store/slices/userSlice";

function WorkoutSessionPage() {
  const [workoutSession, setWorkoutSession] = useState({});
  const [showExerciseForm, setShowExerciseForm] = useState(false);
  const location = useLocation();
  const userId = useSelector(selectUserDTO)?.id;

  const retrieveWorkoutSessionByIdCall = useCallback(() => {
    retrieveWorkoutSessionById(location.state, userId)
      .then((response) => setWorkoutSession(response.data))
      .catch((error) => console.log(error));
  }, [location.state, userId]);

  useEffect(
    () => retrieveWorkoutSessionByIdCall(),
    [retrieveWorkoutSessionByIdCall]
  );

  const handleExerciseUpdated = () => {
    handleCloseExerciseClick();
    retrieveWorkoutSessionByIdCall();
  };

  const handleAddExerciseClick = () => {
    console.log("Page activation");
    setShowExerciseForm(true);
  };

  const handleCloseExerciseClick = () => {
    console.log("Page activation");
    setShowExerciseForm(false);
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
        closeAddExerciseForm={handleCloseExerciseClick}
      />
      {showExerciseForm && (
        <Box>
          <ExerciseForm
            workoutSessionId={workoutSession.id}
            onExerciseAdded={handleExerciseUpdated}
            onFormClose={handleCloseExerciseClick}
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
