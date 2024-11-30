INSERT INTO roles (role)
VALUES
    ('ADMIN'),
    ('USER');

INSERT INTO workout_session (workout_session_name, date)
VALUES
    ('Leg day', '2024-08-29'),
    ('Leg day1', '2024-09-06'),
    ('Leg day2', '2024-09-14'),
    ('Leg day3', '2024-09-21');

INSERT INTO exercise_set (sets, reps, weight, rest)
VALUES
      (1, 5, 100, 180),
      (2, 4, 105, 200),
      (1, 7, 105, 180),
      (2, 6, 110, 200),
      (1, 8, 115, 120),
      (2, 7, 120, 150),
      (1, 9, 125, 120),
      (2, 8, 130, 150);

INSERT INTO exercise (id)
VALUES
    (DEFAULT),
    (DEFAULT),
    (DEFAULT),
    (DEFAULT);

INSERT INTO exercise_type (name)
VALUES
    ('SquatsET'),
    ('arms');


UPDATE exercise
SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day' LIMIT 1),
    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'SquatsET' LIMIT 1)
WHERE id = 1;

UPDATE exercise
SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day1' LIMIT 1),
    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'SquatsET' LIMIT 1)
WHERE id = 2;

UPDATE exercise
SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day2' LIMIT 1),
    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'SquatsET' LIMIT 1)
WHERE id = 3;

UPDATE exercise
SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day3' LIMIT 1),
    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'SquatsET' LIMIT 1)
WHERE id = 4;


UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 1 LIMIT 1)
WHERE id = 1;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 1 LIMIT 1)
WHERE id = 2;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 2 LIMIT 1)
WHERE id = 3;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 2 LIMIT 1)
WHERE id = 4;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 3 LIMIT 1)
WHERE id = 5;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 3 LIMIT 1)
WHERE id = 6;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 4 LIMIT 1)
WHERE id = 7;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 4 LIMIT 1)
WHERE id = 8;


