import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import axios from 'axios';


function ExerciseTable() {

  function testCall () {
    console.log('hi')
    axios.get('http://localhost:8080/exercise')
    .then( (response) => succes(response))
    .catch( (error) => erroras(error))
    .finally( () => console.log('cleanup'))
  }

  function succes (response) {
    console.log(response)
  }
  function erroras (error) {
    console.log(error)
  }

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell align="right">Exercise name</TableCell>
            <TableCell align="right">Sets</TableCell>
            <TableCell align="right">Reps</TableCell>
            <TableCell align="right">Weight</TableCell>
            <TableCell align="right">Rest</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          <TableRow><Button variant="outlined" onClick={testCall}>Outlined</Button></TableRow>
          {/* {rows.map((row) => (
            <TableRow
              key={row.name}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
              <TableCell align="right">{row.calories}</TableCell>
              <TableCell align="right">{row.fat}</TableCell>
              <TableCell align="right">{row.carbs}</TableCell>
              <TableCell align="right">{row.protein}</TableCell>
            </TableRow>
          ))} */}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
export default ExerciseTable;
