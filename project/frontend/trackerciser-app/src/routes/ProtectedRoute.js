import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import { selectLoggedInUser } from "../store/slices/userSlice";

const ProtectedRoute = ({ children }) => {
  const isAuthenticated = useSelector(selectLoggedInUser);

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }
  return children;
};

export default ProtectedRoute;
