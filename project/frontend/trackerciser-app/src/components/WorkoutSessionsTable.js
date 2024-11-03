import Button from "@mui/material/Button";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import * as React from "react";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  retrieveAllWorkoutSessions,
  deleteWorkoutSessionById,
} from "../api/WorkoutSessionApi";
import WorkoutSessionForm from "../forms/WorkoutSessionForm";

function WorkoutSessionsTable() {
  const [workoutSessions, setWorkoutSessions] = useState([]);
  const [showWorkoutSessionForm, setShowWorkoutSessionForm] = useState(false);
  const [editWorkoutSessionId, setEditWorkoutSessionId] = useState(null);
  const [addingNewWorkoutSession, setAddingNewWorkoutSession] = useState(false);
  const navigate = useNavigate();
  useEffect(() => retrieveWorkoutSessionsCall(), []);

  function selectRow(id) {
    navigate("/workoutSession", { state: id });
  }

  function retrieveWorkoutSessionsCall() {
    retrieveAllWorkoutSessions()
      .then((response) => setWorkoutSessions(response.data))
      .catch((error) => console.log(error))
      .finally(() => console.log("passed"));
  }

  function deleteWorkoutSessionByIdCall(id) {
    deleteWorkoutSessionById(id)
      .then(() => {
        retrieveWorkoutSessionsCall();
      })
      .catch((error) => console.log(error))
      .finally(() => console.log("deleted"));
  }
  function handleFormSuccess() {
    setShowWorkoutSessionForm(false);
    setAddingNewWorkoutSession(false);
    setEditWorkoutSessionId(null);
    retrieveWorkoutSessionsCall();
  }
  const handleEditClick = (workoutSesionId) => {
    setEditWorkoutSessionId(workoutSesionId);
    setAddingNewWorkoutSession(false);
    setShowWorkoutSessionForm(false);
  };
  const handleAddClick = () => {
    setShowWorkoutSessionForm((prev) => !prev);
    setAddingNewWorkoutSession(true);
    setEditWorkoutSessionId(null);
  };

  return (
    <>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell align="center">Name</TableCell>
              <TableCell align="center">Date</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {workoutSessions.map((workoutSession) => (
              <TableRow
                onClick={() => selectRow(workoutSession.id)}
                hover
                key={workoutSession.id}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                {editWorkoutSessionId === workoutSession.id ? (
                  <TableCell onClick={(e) => e.stopPropagation()}>
                    <WorkoutSessionForm
                      initialWorkoutSessionValues={workoutSession}
                      isAddingNew={addingNewWorkoutSession}
                      onSuccess={handleFormSuccess}
                    />
                  </TableCell>
                ) : (
                  <>
                    <TableCell component="th" scope="row">
                      {workoutSession.id}
                    </TableCell>
                    <TableCell align="center">
                      {workoutSession.workoutSessionName}
                    </TableCell>
                    <TableCell align="center">{workoutSession.date}</TableCell>
                    <TableCell align="center">
                      <Button
                        variant="contained"
                        onClick={(e) => {
                          e.stopPropagation();
                          handleEditClick(workoutSession.id);
                        }}
                      >
                        Edit
                      </Button>
                      <Button
                        variant="contained"
                        color="error"
                        onClick={(e) => {
                          e.stopPropagation();
                          deleteWorkoutSessionByIdCall(workoutSession.id);
                        }}
                      >
                        Delete
                      </Button>
                    </TableCell>
                  </>
                )}
              </TableRow>
            ))}
            {showWorkoutSessionForm && (
              <TableRow>
                <TableCell colSpan={4}>
                  <WorkoutSessionForm
                    onSuccess={handleFormSuccess}
                    isAddingNew={addingNewWorkoutSession}
                  />
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
      <Button variant="contained" onClick={handleAddClick}>
        {showWorkoutSessionForm ? "Cancel" : "Add Workout Session"}
      </Button>
    </>
  );
}
export default WorkoutSessionsTable;
