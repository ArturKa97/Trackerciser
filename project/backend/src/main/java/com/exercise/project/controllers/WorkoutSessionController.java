package com.exercise.project.controllers;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.services.WorkoutSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = " User's Workout Session Management")
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @Operation(description = "Post endpoint for adding a Workout Session to a User",
            summary = "Create and add a new workout session to a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout session successfully created and added to the user, returns the created session back",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkoutSessionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User was not found with the provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping("/{userId}")
    public WorkoutSessionDTO addWorkoutSession(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Workout session object to be created and added", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkoutSessionDTO.class))) @RequestBody WorkoutSessionDTO workoutSessionDTO,
                                               @Parameter(in = ParameterIn.PATH, description = "Id for which User to add the workout session to", required = true)
                                               @PathVariable(value = "userId") Long userId) {
        return workoutSessionService.addWorkoutSession(workoutSessionDTO, userId);
    }

    @Operation(description = "Get endpoint for getting a single User's Workout Session",
            summary = "Access a single workout session from user's workout sessions list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a single workout session that belongs to the user",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkoutSessionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User or Workout Session was not found with the provided Id's", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @GetMapping("/{id}/{userId}")
    public WorkoutSessionDTO getWorkoutSessionById(@Parameter(in = ParameterIn.PATH, description = "Id of the workout session user needs to receive", required = true)
                                                   @PathVariable(value = "id") Long workoutSessionId,
                                                   @Parameter(in = ParameterIn.PATH, description = "Id of the user that wants to receive the workout session", required = true)
                                                   @PathVariable(value = "userId") Long userId) {
        return workoutSessionService.getWorkoutSessionById(workoutSessionId, userId);
    }

    //TODO: Implement a custom  schema wrapper for  Page<WorkoutSessionDTO>
    @Operation(description = "Get endpoint for retrieving all Workout Sessions belonging to User",
            summary = "Retrieve a list of all of user's workout sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all workout sessions belonging to the user, also uses pagination",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User was not found with provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @GetMapping("/{userId}")
    public Page<WorkoutSessionDTO> getAllWorkoutSessions(@Parameter(in = ParameterIn.QUERY, description = "Page selection", required = true)
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @Parameter(in = ParameterIn.QUERY, description = "Number of objects in the page", required = true)
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @Parameter(in = ParameterIn.PATH, description = "Id of the user to receive the workout sessions from", required = true)
                                                         @PathVariable(value = "userId") Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        return workoutSessionService.getAllWorkoutSessions(pageable, userId);
    }

    @Operation(description = "Get endpoint for retrieving all Workout Sessions in certain time period, belonging to User",
            summary = "Retrieve all of user's workout sessions in specified time period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns workout sessions list in specified date period",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WorkoutSessionDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User was not found with provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @GetMapping("/dates/{userId}")
    public List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(@Parameter(in = ParameterIn.QUERY, description = "Starting date point for selecting the date period", required = true)
                                                                     @RequestParam String fromDate,
                                                                     @Parameter(in = ParameterIn.QUERY, description = "End date point for selecting the date period")
                                                                     @RequestParam String toDate,
                                                                     @Parameter(in = ParameterIn.PATH, description = "Id of the user to receive the workout sessions from")
                                                                     @PathVariable(value = "userId") Long userId) {
        LocalDate parsedFromDate = LocalDate.parse(fromDate);
        LocalDate parsedToDate = LocalDate.parse(toDate);
        return workoutSessionService.getAllWorkoutSessionsBetweenDates(parsedFromDate, parsedToDate, userId);
    }

    @Operation(description = "Delete endpoint for deleting a single Workout Session, belonging to User",
            summary = "Remove a single workout session from the user's workout sessions list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletes the selected workout session from the selected user", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User or Workout Session was not found with the provided Id's", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @DeleteMapping("/{id}/{userId}")
    public void deleteWorkoutSessionById(@Parameter(in = ParameterIn.PATH, description = "Id of the selected workout session to delete", required = true)
                                         @PathVariable(value = "id") Long workoutSessionId,
                                         @Parameter(in = ParameterIn.PATH, description = "Id of the user from which to delete the workout session from", required = true)
                                         @PathVariable(value = "userId") Long userId) {
        workoutSessionService.deleteWorkoutSessionById(workoutSessionId, userId);
    }

    @Operation(description = "Put endpoint for editing a single Workout Session, belonging to User",
            summary = "Edit a single workout session from the user's workout sessions list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates the selected workout sessions values with the new ones provided, and returns the updated workout session",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkoutSessionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User or Workout Session was not found with the provided Id's", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PutMapping("/{id}/{userId}")
    public WorkoutSessionDTO updateWorkoutSessionById(@Parameter(in = ParameterIn.PATH, description = "Id of the original workout session to be edited", required = true)
                                                      @PathVariable(value = "id") Long workoutSessionId,
                                                      @Parameter(in = ParameterIn.PATH, description = "Id of the user of whose workout session to edit", required = true)
                                                      @PathVariable(value = "userId") Long userId,
                                                      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Workout session object with new values to replace the original ones",
                                                              required = true, content = @Content(mediaType = "application/json",
                                                              schema = @Schema(implementation = WorkoutSessionDTO.class))) @RequestBody WorkoutSessionDTO updatedWorkoutSession) {
        return workoutSessionService.updateWorkoutSessionById(workoutSessionId, userId, updatedWorkoutSession);
    }

}
