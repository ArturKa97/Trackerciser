import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import * as React from "react";
import { useState } from "react";
import ExerciseSetForm from "../forms/ExerciseSetForm";
import { removeExerciseSetById } from "../api/ExerciseSetApi";
import { removeExerciseFromWorkoutSession } from "../api/ExerciseApi";
import ClearIcon from "@mui/icons-material/Clear";
import EditIcon from "@mui/icons-material/Edit";
import AddIcon from "@mui/icons-material/Add";
import DeleteIcon from "@mui/icons-material/Delete";
import {
  AddActionButton,
  DeleteOrCloseActionButton,
  EditActionButton,
  RightAlingTableCell,
  TenWidthTableCell,
  TwentyRightAlignWidthTableCell,
  TwentyWidthTableCell,
} from "../styles/StyledComponents";
import { useSelector } from "react-redux";
import { selectUserDTO } from "../store/slices/userSlice";

function ExerciseTable({
  workoutSessionExercises,
  workoutSessionId,
  onExerciseUpdated,
  closeAddExerciseForm,
}) {
  const [editingExerciseId, setEditingExerciseId] = useState(null);
  const [editingExerciseSetId, setEditingExerciseSetId] = useState(null);
  const [addingNewExercise, setAddingNewExercise] = useState(false);
  const exercises = workoutSessionExercises;
  const userId = useSelector(selectUserDTO)?.id;

  const handleEditClick = (exerciseId, exerciseSetId = null) => {
    setEditingExerciseId(exerciseId);
    setEditingExerciseSetId(exerciseSetId);
    setAddingNewExercise(false);
  };

  const handleAddClick = (exerciseId) => {
    setEditingExerciseId(exerciseId);
    setEditingExerciseSetId(null);
    setAddingNewExercise(true);
    closeAddExerciseForm();
  };

  const handleFormClose = () => {
    setEditingExerciseId(null);
    setEditingExerciseSetId(null);
    setAddingNewExercise(false);
    onExerciseUpdated();
  };

  function removeExerciseSetByIdCall(exerciseSetId) {
    removeExerciseSetById(exerciseSetId)
      .then(() => onExerciseUpdated())
      .catch((error) => console.log(error));
  }

  function removeExerciseFromWorkoutSessionCall(workoutSessionId, exerciseId) {
    removeExerciseFromWorkoutSession(workoutSessionId, exerciseId, userId)
      .then(() => onExerciseUpdated())
      .catch((error) => console.log(error));
  }

  return (
    exercises && (
      <Table aria-label="exercises table">
        <TableHead>
          <TableRow>
            <TenWidthTableCell></TenWidthTableCell>
            <TwentyWidthTableCell>Exercise name</TwentyWidthTableCell>
            <TenWidthTableCell>Sets</TenWidthTableCell>
            <TenWidthTableCell>Reps</TenWidthTableCell>
            <TenWidthTableCell>Weight</TenWidthTableCell>
            <TenWidthTableCell>Rest</TenWidthTableCell>
            <TenWidthTableCell>Duration</TenWidthTableCell>
            <TwentyRightAlignWidthTableCell>
              Actions
            </TwentyRightAlignWidthTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {exercises.map((exercise) => {
            const exerciseSets = exercise.exerciseSetsDTO || [];
            return (
              <React.Fragment key={exercise.id}>
                {exerciseSets && exerciseSets.length > 0 ? (
                  exerciseSets.map((set, index) => (
                    <TableRow style={{ cursor: "pointer" }} key={set.id}>
                      {index === 0 ? (
                        <>
                          <TenWidthTableCell>
                            <DeleteOrCloseActionButton
                              aria-label="delete"
                              onClick={() =>
                                removeExerciseFromWorkoutSessionCall(
                                  workoutSessionId,
                                  exercise.id
                                )
                              }
                            >
                              <ClearIcon />
                            </DeleteOrCloseActionButton>
                          </TenWidthTableCell>
                          <TwentyWidthTableCell>
                            {exercise.exerciseTypeDTO.name}
                          </TwentyWidthTableCell>
                        </>
                      ) : (
                        <>
                          <TableCell />
                          <TableCell />
                        </>
                      )}
                      {exerciseSets.length > 0 &&
                      editingExerciseId === exercise.id &&
                      editingExerciseSetId === set.id ? (
                        <TableCell colSpan={8}>
                          <ExerciseSetForm
                            exerciseSets={set}
                            onFormClose={handleFormClose}
                            isAddingNew={false}
                          />
                        </TableCell>
                      ) : (
                        <>
                          <TenWidthTableCell>{set.sets}</TenWidthTableCell>
                          <TenWidthTableCell>{set.reps}</TenWidthTableCell>
                          <TenWidthTableCell>{set.weight}</TenWidthTableCell>
                          <TenWidthTableCell>{set.rest}</TenWidthTableCell>
                          <TenWidthTableCell>{set.duration}</TenWidthTableCell>
                          <TwentyRightAlignWidthTableCell>
                            <EditActionButton
                              onClick={() =>
                                handleEditClick(exercise.id, set.id)
                              }
                            >
                              <EditIcon />
                            </EditActionButton>
                            <DeleteOrCloseActionButton
                              onClick={() => removeExerciseSetByIdCall(set.id)}
                            >
                              <DeleteIcon />
                            </DeleteOrCloseActionButton>
                          </TwentyRightAlignWidthTableCell>
                        </>
                      )}
                    </TableRow>
                  ))
                ) : (
                  <>
                    <TenWidthTableCell>
                      <DeleteOrCloseActionButton
                        aria-label="delete"
                        onClick={() =>
                          removeExerciseFromWorkoutSessionCall(
                            workoutSessionId,
                            exercise.id
                          )
                        }
                      >
                        <ClearIcon />
                      </DeleteOrCloseActionButton>
                    </TenWidthTableCell>
                    <TwentyWidthTableCell>
                      {exercise.exerciseTypeDTO.name}
                    </TwentyWidthTableCell>
                    <TenWidthTableCell />
                    <TenWidthTableCell />
                    <TenWidthTableCell />
                    <TenWidthTableCell />
                    <TenWidthTableCell />
                    <TwentyRightAlignWidthTableCell />
                  </>
                )}
                {addingNewExercise && editingExerciseId === exercise.id ? (
                  <TableRow>
                    <TableCell />
                    <TableCell />
                    <TableCell colSpan={8}>
                      <ExerciseSetForm
                        exerciseId={exercise.id}
                        exerciseSets={null}
                        onFormClose={handleFormClose}
                        isAddingNew={true}
                      />
                    </TableCell>
                  </TableRow>
                ) : (
                  <TableRow>
                    <RightAlingTableCell colSpan={8}>
                      <AddActionButton
                        onClick={() => handleAddClick(exercise.id)}
                        disabled={!!editingExerciseId}
                      >
                        <AddIcon />
                      </AddActionButton>
                    </RightAlingTableCell>
                  </TableRow>
                )}
              </React.Fragment>
            );
          })}
        </TableBody>
      </Table>
    )
  );
}
export default ExerciseTable;
