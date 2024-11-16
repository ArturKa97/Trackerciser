INSERT INTO user (username, password)
VALUES
    ('user', 'user'),
    ('admin', 'admin');

INSERT INTO roles (role)
VALUES
    ('ADMIN'),
    ('USER');

INSERT INTO user_role (user_id, role_id) VALUES (1, 'USER');
INSERT INTO user_role (user_id, role_id) VALUES (2, 'ADMIN');
INSERT INTO user_role (user_id, role_id) VALUES (2, 'USER');

INSERT INTO workout_session (workout_session_name, date)
VALUES
    ('Leg day', '2024-08-29'),
    ('Leg day', '2024-09-29'),
    ('Arm day', '2024-08-28'),
    ('Arm day', '2024-09-28');

INSERT INTO exercise_set (sets, reps, weight, rest)
VALUES
      (1, 1, 1, 1),
      (2, 2, 2, 2),
      (3, 3, 3, 3),
      (4, 4, 4, 4);

INSERT INTO exercise (id)  -- This is likely redundant, just removing 'id' column here
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
SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day' LIMIT 1),
    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'SquatsET' LIMIT 1)
WHERE id = 2;

UPDATE exercise
SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Arm day' LIMIT 1),
    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'arms' LIMIT 1)
WHERE id = 3;

UPDATE exercise
SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Arm day' LIMIT 1),
    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'arms' LIMIT 1)
WHERE id = 4;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 1 LIMIT 1)
WHERE id = 1;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 2 LIMIT 1)
WHERE id = 2;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 2 LIMIT 1)
WHERE id = 3;

UPDATE exercise_set
SET exercise_id = (SELECT id FROM exercise WHERE id = 2 LIMIT 1)
WHERE id = 4;


