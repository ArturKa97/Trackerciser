import HTTP from "./"

export function retrieveAllWorkoutSessions() {
    return HTTP.get('/workout_session')
}