import { Route, Routes } from "react-router-dom";
import ExerciseTable from "../components/ExerciseTable";
import WorkoutSessionPage from "../components/WorkoutSessionPage";
import WorkoutSessionsTable from "../components/WorkoutSessionsTable";
import WorkoutSessionForm from "../forms/WorkoutSessionForm";
import ExerciseForm from "../forms/ExerciseForm";

function AppRoutes() {
  return (
    <Routes>
      <Route path="/exercises" element={<ExerciseTable />} />
      <Route path="/workoutSessions" element={<WorkoutSessionsTable />} />
      <Route path="/workoutSession" element={<WorkoutSessionPage />} />
      <Route path="/workoutSessionForm" element={<WorkoutSessionForm />} />
      <Route path="/exerciseForm" element={<ExerciseForm />} />
    </Routes>
  );
}

export default AppRoutes;
