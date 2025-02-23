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
  FourtyWidthTableCell,
  MainContainer,
  PaginationBox,
  TextAlignCenterBox,
  TwentyRightAlignWidthTableCell,
} from "../styles/StyledComponents";
import EditIcon from "@mui/icons-material/Edit";
import AddIcon from "@mui/icons-material/Add";
import DeleteIcon from "@mui/icons-material/Delete";
import { Pagination, Typography } from "@mui/material";

function WorkoutSessionsTable() {
  const [workoutSessions, setWorkoutSessions] = useState([]);
  const [showWorkoutSessionForm, setShowWorkoutSessionForm] = useState(false);
  const [editWorkoutSessionId, setEditWorkoutSessionId] = useState(null);
  const [addingNewWorkoutSession, setAddingNewWorkoutSession] = useState(false);
  const [pagination, setPagination] = useState({
    first: true,
    last: false,
    number: 0,
    size: 10,
    numberOfElements: 0,
    totalPages: 0,
  });
  const navigate = useNavigate();

  useEffect(
    () => retrieveWorkoutSessionsCall(pagination.number, pagination.size),
    [pagination.number, pagination.size]
  );

  function selectRow(id) {
    navigate("/workoutSession", { state: id });
  }

  function retrieveWorkoutSessionsCall(number, size) {
    retrieveAllWorkoutSessions(number, size)
      .then((response) => {
        setWorkoutSessions(response.data.content);
        setPagination((prevState) => ({
          ...prevState,
          number: response.data.number,
          totalPages: response.data.totalPages,
          numberOfElements: response.data.numberOfElements,
          first: number === 0,
          last:
            number === response.data.totalPages - 1 ||
            response.data.totalPages === 1,
        }));
      })
      .catch((error) => console.log(error));
  }

  function deleteWorkoutSessionByIdCall(id) {
    deleteWorkoutSessionById(id)
      .then(() => {
        pagination.last && !pagination.first && pagination.numberOfElements <= 1
          ? retrieveWorkoutSessionsCall(pagination.number - 1, pagination.size)
          : retrieveWorkoutSessionsCall(pagination.number, pagination.size);
      })
      .catch((error) => console.log(error));
  }

  const handlePageChange = (event, page) => {
    setPagination((prevState) => ({
      ...prevState,
      number: page - 1,
    }));
  };

  const handleFormSuccess = () => {
    setShowWorkoutSessionForm(false);
    setAddingNewWorkoutSession(false);
    setEditWorkoutSessionId(null);
    retrieveWorkoutSessionsCall(pagination.number, pagination.size);
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
              <FourtyWidthTableCell>Name</FourtyWidthTableCell>
              <FourtyWidthTableCell>Date</FourtyWidthTableCell>
              <TwentyRightAlignWidthTableCell>
                Actions
              </TwentyRightAlignWidthTableCell>
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
                    <FourtyWidthTableCell>
                      {workoutSession.workoutSessionName}
                    </FourtyWidthTableCell>
                    <FourtyWidthTableCell>
                      {workoutSession.date}
                    </FourtyWidthTableCell>
                    <TwentyRightAlignWidthTableCell>
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
                    </TwentyRightAlignWidthTableCell>
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
        <PaginationBox>
          <Pagination
            count={pagination.totalPages}
            page={pagination.number + 1}
            onChange={handlePageChange}
            variant="outlined"
            shape="rounded"
          />
        </PaginationBox>
      </MainContainer>
    </>
  );
}
export default WorkoutSessionsTable;
