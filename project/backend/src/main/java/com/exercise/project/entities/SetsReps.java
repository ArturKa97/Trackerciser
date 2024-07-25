package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sets_reps")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SetsReps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sets")
    private Long sets;

    @Column(name = "reps")
    private Long reps;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "rest")
    private Long rest;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;
}
