import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import * as React from "react";
import { Button } from "@mui/material";

function ExerciseTable({ workoutSessionExercises, onEditExerciseInfo }) {
  const exercises = workoutSessionExercises;

  return (
    exercises && (
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
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
              const exerciseInfo = exercise.exerciseInfo;
              return (
                <React.Fragment key={exercise.id}>
                  <TableRow>
                    <TableCell component="th" scope="row">
                      {exercise.id}
                    </TableCell>
                    <TableCell align="center">
                      {exercise.exerciseType.name}
                    </TableCell>
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
                        <Button
                          onClick={() =>
                            onEditExerciseInfo(exercise, exerciseInfo[0])
                          }
                          variant="outlined"
                        >
                          Edit
                        </Button>
                      )}
                    </TableCell>
                  </TableRow>
                  {exerciseInfo.slice(1).map((info) => (
                    <TableRow key={info.id}>
                      <TableCell component="th" scope="row" />
                      <TableCell align="center" />
                      <TableCell align="center">{info.sets}</TableCell>
                      <TableCell align="center">{info.reps}</TableCell>
                      <TableCell align="center">{info.weight}</TableCell>
                      <TableCell align="center">{info.rest}</TableCell>
                      <TableCell align="center">
                        <Button
                          onClick={() => onEditExerciseInfo(exercise, info)}
                          variant="outlined"
                        >
                          Edit S
                        </Button>
                      </TableCell>
                    </TableRow>
                  ))}
                  <TableRow>
                    <TableCell colSpan={7} align="center">
                      <Button
                        onClick={() => onEditExerciseInfo(exercise, null)}
                        variant="contained"
                        color="primary"
                      >
                        Add
                      </Button>
                    </TableCell>
                  </TableRow>
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
