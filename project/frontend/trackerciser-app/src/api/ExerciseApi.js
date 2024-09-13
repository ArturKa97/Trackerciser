import axios from 'axios';

export function retrieveAllExercises() {
    return axios.get('http://localhost:8080/exercise')
}