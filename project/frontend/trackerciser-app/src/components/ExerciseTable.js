import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import * as React from "react";
import { useState } from "react";
import { Button } from "@mui/material";
import ExerciseSetForm from "../forms/ExerciseSetForm";
import { removeExerciseSetById } from "../api/ExerciseSetApi";

function ExerciseTable({ workoutSessionExercises, onExerciseUpdated, closeAddExerciseForm}) {
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
      .finally(() =>
        console.log("cleanup")
      );
  }

  return (
    exercises && (
      <TableContainer
        component={Paper}
        sx={{ width: "100%", overflowX: "auto" }}
      >
        <Table sx={{ minWidth: 650 }} aria-label="exercise table">
          <TableHead>
            <TableRow sx={{ minHeight: "48px" }}>
              <TableCell>ID</TableCell>
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
              const exerciseSets = exercise.exerciseSets || [];
              return (
                <React.Fragment key={exercise.id}>
                  <TableRow sx={{ minHeight: "48px" }}>
                    <TableCell component="th" scope="row">
                      {exercise.id}
                    </TableCell>
                    <TableCell align="center">
                      {exercise.exerciseType.name}
                    </TableCell>
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
                                  handleEditClick(
                                    exercise.id,
                                    exerciseSets[0].id
                                  )
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
                      <TableRow key={set.id} sx={{ minHeight: "48px" }}>
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
                            <TableCell component="th" scope="row" />
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
                                Edit S
                              </Button>
                              <Button
                                variant="outlined"
                                onClick={() =>
                                  removeExerciseSetByIdCall(set.id)
                                }
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
                      <TableRow sx={{ minHeight: "48px" }}>
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
                      <TableRow sx={{ minHeight: "48px" }}>
                        <TableCell colSpan={7} align="center">
                          <Button
                            onClick={() => handleAddClick(exercise.id)}
                            variant="contained"
                            color="primary"
                          >
                            Add
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
      </TableContainer>
    )
  );
}
export default ExerciseTable;
