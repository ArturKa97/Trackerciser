package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseSetDTO;
import com.exercise.project.services.ExerciseSetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exerciseSet")
@RequiredArgsConstructor
@Tag(name = "Exercise Set Management For User's Exercises From Workout Sessions")
public class ExerciseSetController {

    private final ExerciseSetService exerciseSetService;

    @Operation(description = "Post endpoint for adding the Exercise Set information to the Exercise",
            summary = "Create and add the exercise set information like sets, reps, etc... for selected exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully adds exercise set information to the selected exercise and then returns it",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseSetDTO.class))),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "Exercise was not found with provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping("/{exerciseId}")
    public ExerciseSetDTO addExerciseSetToExercise(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exercise Set object to add to the selected exercise", required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseSetDTO.class)))
                                                   @RequestBody ExerciseSetDTO exerciseSetDTO,
                                                   @Parameter(in = ParameterIn.PATH, description = "Id of the exercise to add the exercise set info to", required = true)
                                                   @PathVariable(value = "exerciseId") Long exerciseId) {
        return exerciseSetService.addExerciseSetToExercise(exerciseSetDTO, exerciseId);
    }

    @Operation(description = "Delete endpoint for deleting the Exercise Set information from the Exercise",
            summary = "Delete the exercise set information like sets, reps, etc... from the exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deletes exercise set from the selected exercise",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @DeleteMapping("/{exerciseSetId}")
    public void removeExerciseSetById(@Parameter(in = ParameterIn.PATH, description = "Id of the exercise set to be deleted", required = true) @PathVariable(value = "exerciseSetId") Long exerciseSetId) {
        exerciseSetService.removeExerciseSetById(exerciseSetId);
    }

    @Operation(description = "Put endpoint for updating the Exercise Set information in the Exercise",
            summary = "Edit the selected exercise set in the exercise with the new values")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updates the selected exercise set with new values and returns it",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseSetDTO.class))),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "Exercise set was not found with provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PutMapping("/{exerciseSetId}")
    public ExerciseSetDTO updateExerciseSetById(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exercise set object with new values to be updated", required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseSetDTO.class)))
                                                @RequestBody ExerciseSetDTO updatedExerciseSet,
                                                @Parameter(in = ParameterIn.PATH, description = "Id of the exercise set to be updated", required = true)
                                                @PathVariable(value = "exerciseSetId") Long exerciseSetId) {
        return exerciseSetService.updateExerciseSetById(updatedExerciseSet, exerciseSetId);
    }
}
