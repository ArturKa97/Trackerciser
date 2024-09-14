import { Routes, Route } from "react-router-dom";
import ExerciseTable from "../components/ExerciseTable";
import WorkoutSessionsTable from "../components/WorkoutSessionsTable";

function AppRoutes() {
    return (
        <Routes>
            <Route path="/exercises" element={<ExerciseTable/>}/>
            <Route path="/workoutSessions" element={<WorkoutSessionsTable/>}/>
        </Routes>
      );
}

export default AppRoutes;