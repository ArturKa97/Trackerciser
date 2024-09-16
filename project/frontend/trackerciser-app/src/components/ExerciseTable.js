import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import * as React from "react";

function ExerciseTable(props) {
  const exercises = props.workoutSessionExercises;

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
            </TableRow>
          </TableHead>
          <TableBody>
            {exercises.map((exercise) => (
              <TableRow
                key={exercise.id}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {exercise.id}
                </TableCell>
                <TableCell align="center">{exercise.name}</TableCell>
                {exercise.exerciseInfo.map((info) => (
                  <>
                    <TableCell key={info.id} align="center">
                      {info.sets}
                    </TableCell>
                    <TableCell align="center">{info.reps}</TableCell>
                  </>
                ))}
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    )
  );
}
export default ExerciseTable;
