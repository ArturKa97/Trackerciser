import HTTP from "./";

export function addExerciseToWorkoutSession(workoutSessionId, exerciseTypeId) {
  return HTTP.post(`/exercise/${workoutSessionId}/${exerciseTypeId}`);
}

export function removeExerciseFromWorkoutSession(workoutSessionId, exerciseId) {
  return HTTP.delete(`/exercise/${workoutSessionId}/${exerciseId}`);
}
