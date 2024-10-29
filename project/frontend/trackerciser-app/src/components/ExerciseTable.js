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
import SetsRepsForm from "../forms/SetsRepsForm";
import { removeExerciseInfoById } from "../api/SetsRepsApi";

function ExerciseTable({ workoutSessionExercises, onExerciseUpdated }) {
  const [editingExerciseId, setEditingExerciseId] = useState(null);
  const [editingExerciseInfoId, setEditingExerciseInfoId] = useState(null);
  const [addingNewExercise, setAddingNewExercise] = useState(false);
  const exercises = workoutSessionExercises;

  const handleEditClick = (exerciseId, exerciseInfoId = null) => {
    setEditingExerciseId(exerciseId);
    setEditingExerciseInfoId(exerciseInfoId);
    setAddingNewExercise(false);
  };

  const handleAddClick = (exerciseId) => {
    setEditingExerciseId(exerciseId);
    setEditingExerciseInfoId(null);
    setAddingNewExercise(true);
  };

  const handleFormClose = () => {
    setEditingExerciseId(null);
    setEditingExerciseInfoId(null);
    setAddingNewExercise(false);
    onExerciseUpdated();
  };

  function removeExerciseInfoByIdCall(exerciseInfoId) {
    removeExerciseInfoById(exerciseInfoId)
      .then(() => onExerciseUpdated())
      .catch((error) => console.log(error))
      .finally(() =>
        console.log("Removal of exerciseInfoByIdCall function ending")
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
              const exerciseInfo = exercise.exerciseInfo || [];
              return (
                <React.Fragment key={exercise.id}>
                  <TableRow sx={{ minHeight: "48px" }}>
                    <TableCell component="th" scope="row">
                      {exercise.id}
                    </TableCell>
                    <TableCell align="center">
                      {exercise.exerciseType.name}
                    </TableCell>
                    {exerciseInfo.length > 0 &&
                    editingExerciseId === exercise.id &&
                    editingExerciseInfoId === exerciseInfo[0].id ? (
                      <>
                        <TableCell colSpan={7}>
                          <SetsRepsForm
                            exerciseInfo={exerciseInfo[0]}
                            onFormClose={handleFormClose}
                            isAddingNew={false}
                          />
                        </TableCell>
                      </>
                    ) : (
                      <>
                        <TableCell align="center">
                          {exerciseInfo[0]?.sets}
                        </TableCell>
                        <TableCell align="center">
                          {exerciseInfo[0]?.reps}
                        </TableCell>
                        <TableCell align="center">
                          {exerciseInfo[0]?.weight}
                        </TableCell>
                        <TableCell align="center">
                          {exerciseInfo[0]?.rest}
                        </TableCell>
                        <TableCell align="center">
                          {exerciseInfo.length > 0 && (
                            <>
                              <Button
                                onClick={() =>
                                  handleEditClick(
                                    exercise.id,
                                    exerciseInfo[0].id
                                  )
                                }
                                variant="outlined"
                              >
                                Edit
                              </Button>
                              <Button
                                variant="outlined"
                                onClick={() =>
                                  removeExerciseInfoByIdCall(exerciseInfo[0].id)
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

                  {exerciseInfo.length > 1 &&
                    exerciseInfo.slice(1)?.map((info) => (
                      <TableRow key={info.id} sx={{ minHeight: "48px" }}>
                        {editingExerciseId === exercise.id &&
                        editingExerciseInfoId === info.id ? (
                          <>
                            <TableCell colSpan={7}>
                              <SetsRepsForm
                                exerciseInfo={info}
                                onFormClose={handleFormClose}
                                isAddingNew={false}
                              />
                            </TableCell>
                          </>
                        ) : (
                          <>
                            <TableCell component="th" scope="row" />
                            <TableCell align="center" />
                            <TableCell align="center">{info.sets}</TableCell>
                            <TableCell align="center">{info.reps}</TableCell>
                            <TableCell align="center">{info.weight}</TableCell>
                            <TableCell align="center">{info.rest}</TableCell>
                            <TableCell align="center">
                              <Button
                                onClick={() =>
                                  handleEditClick(exercise.id, info.id)
                                }
                                variant="outlined"
                              >
                                Edit S
                              </Button>
                              <Button
                                variant="outlined"
                                onClick={() =>
                                  removeExerciseInfoByIdCall(info.id)
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
                          <SetsRepsForm
                            exerciseId={exercise.id}
                            exerciseInfo={null}
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
