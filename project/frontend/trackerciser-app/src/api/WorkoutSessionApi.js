import HTTP from "./";

export function retrieveAllWorkoutSessions() {
  return HTTP.get("/workout_session");
}

export function retrieveWorkoutSessionById(id) {
  return HTTP.get(`/workout_session/${id}`);
}

export function addWorkoutSession(workoutSession) {
  return HTTP.post("/workout_session", workoutSession, {
    headers: {
      "Content-Type": "application/json",
    },
  });
}

export function deleteWorkoutSessionById(id) {
  return HTTP.delete(`/workout_session/${id}`);
}

export function updateWorkoutSessionById(workoutSessionId, workoutSession) {
  return HTTP.put(`/workout_session/${workoutSessionId}`, workoutSession);
}
