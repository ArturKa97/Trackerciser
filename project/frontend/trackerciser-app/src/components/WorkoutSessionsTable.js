import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
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
import {
  DeleteActionButton,
  EditActionButton,
  MainContainer,
  WorkoutSessionFormStateButton,
} from "../styles/StyledComponents";
import EditIcon from "@mui/icons-material/Edit";
import ClearIcon from "@mui/icons-material/Clear";
import AddIcon from "@mui/icons-material/Add";

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
      <MainContainer>
        <Table aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Date</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {workoutSessions.map((workoutSession) => (
              <TableRow
                onClick={() => selectRow(workoutSession.id)}
                key={workoutSession.id}
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
                    <TableCell>{workoutSession.workoutSessionName}</TableCell>
                    <TableCell>{workoutSession.date}</TableCell>
                    <TableCell>
                      <EditActionButton
                        onClick={(e) => {
                          e.stopPropagation();
                          handleEditClick(workoutSession.id);
                        }}
                      >
                        <EditIcon />
                      </EditActionButton>
                      <DeleteActionButton
                        onClick={(e) => {
                          e.stopPropagation();
                          deleteWorkoutSessionByIdCall(workoutSession.id);
                        }}
                      >
                        <ClearIcon />
                      </DeleteActionButton>
                    </TableCell>
                  </>
                )}
              </TableRow>
            ))}
            {showWorkoutSessionForm && (
              <TableRow>
                <TableCell>
                  <WorkoutSessionForm
                    onSuccess={handleFormSuccess}
                    isAddingNew={addingNewWorkoutSession}
                  />
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
        <WorkoutSessionFormStateButton onClick={handleAddClick}>
          {showWorkoutSessionForm ? <ClearIcon /> : <AddIcon />}
        </WorkoutSessionFormStateButton>
      </MainContainer>
    </>
  );
}
export default WorkoutSessionsTable;
