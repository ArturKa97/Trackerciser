import HTTP from "./";

export function addExerciseInfoToExercise(exerciseId, exerciseInfo) {
  return HTTP.post(`/sets_reps/${exerciseId}`, exerciseInfo);
}

export function removeExerciseInfoById(exerciseInfoId) {
  return HTTP.delete(`/sets_reps/${exerciseInfoId}`);
}

export function updateExerciseInfoById(exerciseInfoId, exerciseInfo) {
  return HTTP.put(`/sets_reps/${exerciseInfoId}`, exerciseInfo);
}