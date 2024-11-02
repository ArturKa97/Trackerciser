import HTTP from ".";

export function addExerciseSetToExercise(exerciseId, exerciseSets) {
  return HTTP.post(`/exerciseSet/${exerciseId}`, exerciseSets);
}

export function removeExerciseSetById(exerciseSetId) {
  return HTTP.delete(`/exerciseSet/${exerciseSetId}`);
}

export function updateExerciseSetById(exerciseSetId, exerciseSets) {
  return HTTP.put(`/exerciseSet/${exerciseSetId}`, exerciseSets);
}