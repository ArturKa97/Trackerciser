import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";


function WorkoutSessionsTable() {
  
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell align="center">Name</TableCell>
            <TableCell align="center">Date</TableCell>
          </TableRow>
        </TableHead>
        {/* <TableBody>
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
                <TableCell key={info.id} align="center">
                  {info.sets}
                </TableCell>
              ))}
            </TableRow>
          ))}
        </TableBody> */}
      </Table>
    </TableContainer>
  );
}
export default WorkoutSessionsTable;
