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
  BigTableAddButton,
  DeleteOrCloseActionButton,
  EditActionButton,
  MainContainer,
  TextAlignCenterBox,
} from "../styles/StyledComponents";
import EditIcon from "@mui/icons-material/Edit";
import AddIcon from "@mui/icons-material/Add";
import DeleteIcon from "@mui/icons-material/Delete";
import { Typography } from "@mui/material";

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
  const handleFormSuccess = () => {
    setShowWorkoutSessionForm(false);
    setAddingNewWorkoutSession(false);
    setEditWorkoutSessionId(null);
    retrieveWorkoutSessionsCall();
  };
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

  const handleCloseClick = () => {
    setEditWorkoutSessionId(null);
    setShowWorkoutSessionForm(false);
    setAddingNewWorkoutSession(false);
  };

  return (
    <>
      <MainContainer>
        <Table aria-label="workout sessions table">
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Date</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {workoutSessions.length === 0 && (
              <TableRow>
                <TableCell colSpan={3}>
                  <TextAlignCenterBox>
                    <Typography>NO WORKOUT SESSIONS FOUND</Typography>
                  </TextAlignCenterBox>
                </TableCell>
              </TableRow>
            )}
            {workoutSessions.map((workoutSession) => (
              <TableRow
                onClick={() => selectRow(workoutSession.id)}
                key={workoutSession.id}
              >
                {editWorkoutSessionId === workoutSession.id ? (
                  <TableCell colSpan={3} onClick={(e) => e.stopPropagation()}>
                    <WorkoutSessionForm
                      initialWorkoutSessionValues={workoutSession}
                      isAddingNew={addingNewWorkoutSession}
                      onSuccess={handleFormSuccess}
                      onClose={handleCloseClick}
                    />
                  </TableCell>
                ) : (
                  <>
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
                      <DeleteOrCloseActionButton
                        onClick={(e) => {
                          e.stopPropagation();
                          deleteWorkoutSessionByIdCall(workoutSession.id);
                        }}
                      >
                        <DeleteIcon />
                      </DeleteOrCloseActionButton>
                    </TableCell>
                  </>
                )}
              </TableRow>
            ))}
            {showWorkoutSessionForm && (
              <TableRow>
                <TableCell colSpan={3}>
                  <WorkoutSessionForm
                    onSuccess={handleFormSuccess}
                    isAddingNew={addingNewWorkoutSession}
                    onClose={handleCloseClick}
                  />
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
        <BigTableAddButton
          onClick={handleAddClick}
          disabled={showWorkoutSessionForm}
        >
          <AddIcon />
        </BigTableAddButton>
      </MainContainer>
    </>
  );
}
export default WorkoutSessionsTable;
