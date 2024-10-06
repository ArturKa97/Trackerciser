import { Route, Routes } from "react-router-dom";
import ExerciseTable from "../components/ExerciseTable";
import WorkoutSessionPage from "../components/WorkoutSessionPage";
import WorkoutSessionsTable from "../components/WorkoutSessionsTable";
import WorkoutSessionForm from "../forms/WorkoutSessionForm";

function AppRoutes() {
  return (
    <Routes>
      <Route path="/exercises" element={<ExerciseTable />} />
      <Route path="/workoutSessions" element={<WorkoutSessionsTable />} />
      <Route path="/workoutSession" element={<WorkoutSessionPage />} />
      <Route path="/workoutSessionForm" element={<WorkoutSessionForm />} />
    </Routes>
  );
}

export default AppRoutes;
