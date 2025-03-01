package com.exercise.project.controllers;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.services.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/workout_session")
@RequiredArgsConstructor
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @PostMapping("/{userId}")
    public WorkoutSessionDTO addWorkoutSession(@RequestBody WorkoutSessionDTO workoutSessionDTO, @PathVariable(value = "userId") Long userId) {
        return workoutSessionService.addWorkoutSession(workoutSessionDTO, userId);
    }

    @GetMapping("/{id}")
    public WorkoutSessionDTO getWorkoutSessionById(@PathVariable(value = "id") Long id) {
        return workoutSessionService.getWorkoutSessionById(id);
    }

    @GetMapping
    public Page<WorkoutSessionDTO> getAllWorkoutSessions(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return workoutSessionService.getAllWorkoutSessions(pageable);
    }

    @GetMapping("/dates")
    public List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(@RequestParam String fromDate, @RequestParam String toDate) {
        LocalDate parsedFromDate = LocalDate.parse(fromDate);
        LocalDate parsedToDate = LocalDate.parse(toDate);
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
