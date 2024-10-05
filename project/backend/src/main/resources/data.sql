INSERT INTO workout_session (id ,name, date)
VALUES
    (1, 'Leg day', '2024-08-29'),
    (2, 'Leg day', '2024-09-29'),
    (3, 'Arm day', '2024-08-28'),
    (4, 'Arm day', '2024-09-28');

INSERT INTO sets_reps (id, sets, reps, weight, rest)
VALUES
      (1, 1, 1, 1, 1),
      (2, 2, 2, 2, 2),
      (3, 3, 3, 3, 3),
      (4, 4, 4, 4, 4);

INSERT INTO exercise (id ,name)
VALUES
    (1, 'SquatsE'),
    (2, 'SquatsE'),
    (3, 'Leg extensions'),
    (4, 'Leg extensions');


INSERT INTO exercise_type (id, name)
VALUES
    (1, 'SquatsET'),
    (2, 'arms');

UPDATE exercise
SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session.id = 1),
exercise_type_id = (SELECT id FROM exercise_type WHERE exercise_type.id = 1)
WHERE exercise.id = 1;

UPDATE exercise
SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session.id = 2),
exercise_type_id = (SELECT id FROM exercise_type WHERE exercise_type.id = 1)
WHERE exercise.id = 2;

UPDATE sets_reps
SET exercise_id = (SELECT id FROM exercise WHERE exercise.id = 1)
WHERE sets_reps.id = 1;

UPDATE sets_reps
SET exercise_id = (SELECT id FROM exercise WHERE exercise.id = 2)
WHERE sets_reps.id = 2;

UPDATE sets_reps
SET exercise_id = (SELECT id FROM exercise WHERE exercise.id = 2)
WHERE sets_reps.id = 3;

UPDATE sets_reps
SET exercise_id = (SELECT id FROM exercise WHERE exercise.id = 2)
WHERE sets_reps.id = 4;


