import HTTP from "./";

export function retrieveAllWorkoutSessions(page, size) {
  return HTTP.get("/workout_session", {
    params: {
      page: page,
      size: size,
    },
  });
}

export function retrieveWorkoutSessionById(id) {
  return HTTP.get(`/workout_session/${id}`);
}

export function retrieveAllWorkoutSessionsBetweenDates(fromDate, toDate) {
  return HTTP.get("/workout_session/dates", {
    params: {
      fromDate: fromDate,
      toDate: toDate,
    },
  });
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
