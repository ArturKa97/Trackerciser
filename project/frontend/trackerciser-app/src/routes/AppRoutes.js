import { Routes, Route } from "react-router-dom";
import ExerciseTable from "../components/ExerciseTable";
import WorkoutSessionsTable from "../components/WorkoutSessionsTable";
import WorkoutSession from "../components/WorkoutSession";

function AppRoutes() {
    return (
        <Routes>
            <Route path="/exercises" element={<ExerciseTable/>}/>
            <Route path="/workoutSessions" element={<WorkoutSessionsTable/>}/>
            <Route path="/workoutSession" element={<WorkoutSession/>}/>
        </Routes>
      );
}

export default AppRoutes;