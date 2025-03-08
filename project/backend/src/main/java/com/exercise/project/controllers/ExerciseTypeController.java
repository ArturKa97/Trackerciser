package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseTypeDTO;
import com.exercise.project.services.ExerciseTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise_type")
@Tag(name = "Exercise Types Access")
public class ExerciseTypeController {

    private final ExerciseTypeService exerciseTypeService;

    @Operation(description = "Get endpoint for receiving all Exercise Types",
            summary = "Fetches a list of all available exercise types for a user choose from. This is a part of exercise creation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returns the list of all exercise types",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExerciseTypeDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @GetMapping
    public List<ExerciseTypeDTO> getAllExerciseTypes() {
        return exerciseTypeService.getAllExerciseTypes();

    }
}
