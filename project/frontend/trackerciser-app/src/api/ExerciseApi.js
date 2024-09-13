import HTTP from "./"

export function retrieveAllExercises() {
    return HTTP.get('/exercise')
}