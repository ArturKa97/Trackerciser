import HTTP from "./";

export function retrieveAllWorkoutSessions(page, size, userId) {
  return HTTP.get(`/workout_session/${userId}`, {
    params: {
      page: page,
      size: size,
    },
  });
}

export function retrieveWorkoutSessionById(id, userId) {
  return HTTP.get(`/workout_session/${id}/${userId}`);
}

export function retrieveAllWorkoutSessionsBetweenDates(
  fromDate,
  toDate,
  userId
) {
  return HTTP.get(`/workout_session/dates/${userId}`, {
    params: {
      fromDate: fromDate,
      toDate: toDate,
    },
  });
}

export function addWorkoutSession(workoutSession, userId) {
  return HTTP.post(`/workout_session/${userId}`, workoutSession, {
    headers: {
      "Content-Type": "application/json",
    },
  });
}

export function deleteWorkoutSessionById(id, userId) {
  return HTTP.delete(`/workout_session/${id}/${userId}`);
}

export function updateWorkoutSessionById(
  workoutSessionId,
  workoutSession,
  userId
) {
  return HTTP.put(
    `/workout_session/${workoutSessionId}/${userId}`,
    workoutSession
  );
}
