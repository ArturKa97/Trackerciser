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

function WorkoutSessionsTable() {
  const [workoutSessions, setWorkoutSessions] = useState([]);
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
                    color="error"
                    onClick={(e) => {
                      e.stopPropagation();
                      deleteWorkoutSessionByIdCall(workoutSession.id);
                    }}
                  >
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <Button
        variant="contained"
        color="success"
        onClick={() => navigate("/workoutSessionForm")}
      >
        Add Workout Session
      </Button>
    </>
  );
}
export default WorkoutSessionsTable;
