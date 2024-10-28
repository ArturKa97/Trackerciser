import HTTP from "./";

export function addExerciseInfoToExercise(exerciseId, exerciseInfo) {
  return HTTP.post(`/sets_reps/${exerciseId}`, exerciseInfo);
}
