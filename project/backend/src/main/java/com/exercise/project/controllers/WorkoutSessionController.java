package com.exercise.project.controllers;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.services.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/workout_session")
@RequiredArgsConstructor
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @PostMapping
    public WorkoutSessionDTO addWorkoutSession(@RequestBody WorkoutSessionDTO workoutSessionDTO) {
        return workoutSessionService.addWorkoutSession(workoutSessionDTO);
    }

    @GetMapping("/{id}")
    public WorkoutSessionDTO getWorkoutSessionById(@PathVariable(value = "id") Long id) {
        return workoutSessionService.getWorkoutSessionById(id);
    }

    @GetMapping
    public List<WorkoutSessionDTO> getAllWorkoutSessions() {
        return workoutSessionService.getAllWorkoutSessions();
    }

    @GetMapping("/dates")
    public List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(@RequestParam String fromDate, @RequestParam String toDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date parsedFromDate = dateFormat.parse(fromDate);
        Date parsedToDate = dateFormat.parse(toDate);
        return workoutSessionService.getAllWorkoutSessionsBetweenDates(parsedFromDate, parsedToDate);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkoutSessionById(@PathVariable(value = "id") Long id) {
        workoutSessionService.deleteWorkoutSessionById(id);
    }

    @PutMapping("/{workoutSessionId}")
    public WorkoutSessionDTO updateWorkoutSessionById(@PathVariable(value = "workoutSessionId") Long workoutSessionId, @RequestBody WorkoutSessionDTO updatedWorkoutSession) {
        return workoutSessionService.updateWorkoutSessionById(workoutSessionId, updatedWorkoutSession);
    }

}
