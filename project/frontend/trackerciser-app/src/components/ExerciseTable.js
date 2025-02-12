import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import * as React from "react";
import { useState } from "react";
import { Button } from "@mui/material";
import ExerciseSetForm from "../forms/ExerciseSetForm";
import { removeExerciseSetById } from "../api/ExerciseSetApi";
import { removeExerciseFromWorkoutSession } from "../api/ExerciseApi";
import ClearIcon from "@mui/icons-material/Clear";
import { DeleteOrCloseActionButton } from "../styles/StyledComponents";

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
      .catch((error) => console.log(error))
      .finally(() => console.log("cleanup"));
  }

  function removeExerciseFromWorkoutSessionCall(workoutSessionId, exerciseId) {
    removeExerciseFromWorkoutSession(workoutSessionId, exerciseId)
      .then(() => onExerciseUpdated())
      .catch((error) => console.log(error))
      .finally(() => console.log("exercise removal block end"));
  }

  return (
    exercises && (
      <Table aria-label="exercises table">
        <TableHead>
          <TableRow>
            <TableCell align="center"></TableCell>
            <TableCell align="center">Exercise name</TableCell>
            <TableCell align="center">Sets</TableCell>
            <TableCell align="center">Reps</TableCell>
            <TableCell align="center">Weight</TableCell>
            <TableCell align="center">Rest</TableCell>
            <TableCell align="center">Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {exercises.map((exercise) => {
            const exerciseSets = exercise.exerciseSetsDTO || [];
            return (
              <React.Fragment key={exercise.id}>
                <TableRow>
                  <TableCell>
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
                  </TableCell>
                  <TableCell>{exercise.exerciseTypeDTO.name}</TableCell>
                  {exerciseSets.length > 0 &&
                  editingExerciseId === exercise.id &&
                  editingExerciseSetId === exerciseSets[0].id ? (
                    <>
                      <TableCell colSpan={7}>
                        <ExerciseSetForm
                          exerciseSets={exerciseSets[0]}
                          onFormClose={handleFormClose}
                          isAddingNew={false}
                        />
                      </TableCell>
                    </>
                  ) : (
                    <>
                      <TableCell align="center">
                        {exerciseSets[0]?.sets}
                      </TableCell>
                      <TableCell align="center">
                        {exerciseSets[0]?.reps}
                      </TableCell>
                      <TableCell align="center">
                        {exerciseSets[0]?.weight}
                      </TableCell>
                      <TableCell align="center">
                        {exerciseSets[0]?.rest}
                      </TableCell>
                      <TableCell align="center">
                        {exerciseSets.length > 0 && (
                          <>
                            <Button
                              onClick={() =>
                                handleEditClick(exercise.id, exerciseSets[0].id)
                              }
                              variant="outlined"
                            >
                              Edit
                            </Button>
                            <Button
                              variant="outlined"
                              onClick={() =>
                                removeExerciseSetByIdCall(exerciseSets[0].id)
                              }
                            >
                              Delete
                            </Button>
                          </>
                        )}
                      </TableCell>
                    </>
                  )}
                </TableRow>

                {exerciseSets.length > 1 &&
                  exerciseSets.slice(1)?.map((set) => (
                    <TableRow key={set.id}>
                      {editingExerciseId === exercise.id &&
                      editingExerciseSetId === set.id ? (
                        <>
                          <TableCell colSpan={7}>
                            <ExerciseSetForm
                              exerciseSets={set}
                              onFormClose={handleFormClose}
                              isAddingNew={false}
                            />
                          </TableCell>
                        </>
                      ) : (
                        <>
                          <TableCell align="center" />
                          <TableCell align="center" />
                          <TableCell align="center">{set.sets}</TableCell>
                          <TableCell align="center">{set.reps}</TableCell>
                          <TableCell align="center">{set.weight}</TableCell>
                          <TableCell align="center">{set.rest}</TableCell>
                          <TableCell align="center">
                            <Button
                              onClick={() =>
                                handleEditClick(exercise.id, set.id)
                              }
                              variant="outlined"
                            >
                              Edit
                            </Button>
                            <Button
                              variant="outlined"
                              onClick={() => removeExerciseSetByIdCall(set.id)}
                            >
                              Delete
                            </Button>
                          </TableCell>
                        </>
                      )}
                    </TableRow>
                  ))}
                {addingNewExercise && editingExerciseId === exercise.id ? (
                  <>
                    <TableRow>
                      <TableCell colSpan={7}>
                        <ExerciseSetForm
                          exerciseId={exercise.id}
                          exerciseSets={null}
                          onFormClose={handleFormClose}
                          isAddingNew={true}
                        />
                      </TableCell>
                    </TableRow>
                  </>
                ) : (
                  <>
                    <TableRow>
                      <TableCell colSpan={7}>
                        <Button
                          onClick={() => handleAddClick(exercise.id)}
                          variant="contained"
                          color="primary"
                          disabled={!!editingExerciseId}
                        >
                          Add Set
                        </Button>
                      </TableCell>
                    </TableRow>
                  </>
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
