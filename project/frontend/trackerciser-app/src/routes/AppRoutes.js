import { Route, Routes } from "react-router-dom";
import ExerciseTable from "../components/ExerciseTable";
import WorkoutSessionPage from "../components/WorkoutSessionPage";
import WorkoutSessionsTable from "../components/WorkoutSessionsTable";
import WorkoutSessionForm from "../forms/WorkoutSessionForm";
import ExerciseForm from "../forms/ExerciseForm";
import HomePage from "../components/HomePage";
import { useSelector } from "react-redux";
import { selectLoggedInUser } from "../store/slices/userSlice";
import WorkoutSessionLineChart from "../charts/WorkoutSessionLineChart";
import LoginPage from "../components/LoginPage";
import RegisterPage from "../components/RegisterPage";
import ProtectedRoute from "./ProtectedRoute";

function AppRoutes() {
  const user = useSelector(selectLoggedInUser);
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />

      {!user ? (
        <>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
        </>
      ) : (
        <>
          <Route
            path="/exercises"
            element={
              <ProtectedRoute>
                <ExerciseTable />
              </ProtectedRoute>
            }
          />
          <Route
            path="/workoutSessions"
            element={
              <ProtectedRoute>
                <WorkoutSessionsTable />
              </ProtectedRoute>
            }
          />
          <Route
            path="/workoutSession"
            element={
              <ProtectedRoute>
                <WorkoutSessionPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/workoutSessionForm"
            element={
              <ProtectedRoute>
                <WorkoutSessionForm />
              </ProtectedRoute>
            }
          />
          <Route
            path="/exerciseForm"
            element={
              <ProtectedRoute>
                <ExerciseForm />
              </ProtectedRoute>
            }
          />
          <Route
            path="/chart"
            element={
              <ProtectedRoute>
                <WorkoutSessionLineChart />
              </ProtectedRoute>
            }
          />
        </>
      )}
    </Routes>
  );
}

export default AppRoutes;
