import HTTP from "./";

export function addExerciseToWorkoutSession(workoutSessionId, exerciseTypeId) {
  return HTTP.post(`/exercise/${workoutSessionId}/${exerciseTypeId}`);
}
