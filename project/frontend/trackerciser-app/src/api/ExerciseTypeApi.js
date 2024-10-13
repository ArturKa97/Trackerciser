import HTTP from "./";

export function retrieveAllExerciseTypes() {
  return HTTP.get("/exercise_type");
}
