import HTTP from "./";

export function addExerciseToWorkoutSession(
  workoutSessionId,
  exerciseTypeId,
  userId
) {
  return HTTP.post(`/exercise/${workoutSessionId}/${exerciseTypeId}/${userId}`);
}

export function removeExerciseFromWorkoutSession(
  workoutSessionId,
  exerciseId,
  userId
) {
  return HTTP.delete(`/exercise/${workoutSessionId}/${exerciseId}/${userId}`);
}
