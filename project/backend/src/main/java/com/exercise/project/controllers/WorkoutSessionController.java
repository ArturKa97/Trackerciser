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

    @GetMapping("/{id}/{userId}")
    public WorkoutSessionDTO getWorkoutSessionById(@PathVariable(value = "id") Long workoutSessionId, @PathVariable(value = "userId") Long userId) {
        return workoutSessionService.getWorkoutSessionById(workoutSessionId, userId);
    }

    @GetMapping("/{userId}")
    public Page<WorkoutSessionDTO> getAllWorkoutSessions(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size, @PathVariable(value = "userId") Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        return workoutSessionService.getAllWorkoutSessions(pageable, userId);
    }

    @GetMapping("/dates/{userId}")
    public List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(@RequestParam String fromDate, @RequestParam String toDate, @PathVariable(value = "userId") Long userId) {
        LocalDate parsedFromDate = LocalDate.parse(fromDate);
        LocalDate parsedToDate = LocalDate.parse(toDate);
        return workoutSessionService.getAllWorkoutSessionsBetweenDates(parsedFromDate, parsedToDate, userId);
    }

    @DeleteMapping("/{id}/{userId}")
    public void deleteWorkoutSessionById(@PathVariable(value = "id") Long workoutSessionId, @PathVariable(value = "userId") Long userId) {
        workoutSessionService.deleteWorkoutSessionById(workoutSessionId, userId);
    }

    @PutMapping("/{id}/{userId}")
    public WorkoutSessionDTO updateWorkoutSessionById(@PathVariable(value = "id") Long workoutSessionId, @PathVariable(value = "userId") Long userId, @RequestBody WorkoutSessionDTO updatedWorkoutSession) {
        return workoutSessionService.updateWorkoutSessionById(workoutSessionId, userId, updatedWorkoutSession);
    }

}
