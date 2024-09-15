import HTTP from "./";

export function retrieveAllWorkoutSessions() {
  return HTTP.get("/workout_session");
}

export function retrieveWorkoutSessionById(id) {
  return HTTP.get(`/workout_session/${id}`);
}
