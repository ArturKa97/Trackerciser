INSERT INTO roles (role)
VALUES
    ('ADMIN'),
    ('USER');

--INSERT INTO workout_session (workout_session_name, date)
--VALUES
--    ('Leg day', '2024-08-29'),
--    ('Leg day1', '2024-09-06'),
--    ('Leg day2', '2024-09-14'),
--    ('Leg day3', '2024-09-21');
--
--INSERT INTO exercise_set (sets, reps, weight, rest, duration)
--VALUES
--      (1, 5, 100, 180, 30),
--      (2, 4, 105, 200, 31),
--      (1, 7, 105, 180, 32),
--      (2, 6, 110, 200, 33),
--      (1, 8, 115, 120, 34),
--      (2, 7, 120, 150, 29),
--      (1, 9, 125, 120, 28),
--      (2, 8, 130, 150, 27),
--
--      (1, 10, 60, 120, 21),
--      (2, 8, 68, 140, 22),
--      (1, 12, 68, 110, 23),
--      (2, 10, 74, 130, 24),
--      (1, 10, 80, 100, 29),
--      (2, 8, 86, 110, 28),
--      (1, 9, 92, 100, 27),
--      (2, 8, 97, 100, 26);
--
--INSERT INTO exercise (id)
--VALUES
--    (DEFAULT),
--    (DEFAULT),
--    (DEFAULT),
--    (DEFAULT),
--    (DEFAULT),
--    (DEFAULT),
--    (DEFAULT),
--    (DEFAULT);

INSERT INTO exercise_type (name)
VALUES
--Legs
    ('Barbell Squats'),
    ('Smith Machine Squats'),
    ('Goblet Squats'),
    ('Bulgarian Split Squats'),
    ('Hack Squats'),
    ('Front Squats'),
    ('Split Squats'),
    ('Leg Press'),
    ('Single Leg, Leg Press'),
    ('Lunges'),
    ('Leg Extensions'),
    ('Single Leg Extensions'),
--Hamstrings, Glutes
    ('Leg Curls'),
    ('Single Leg Curls'),
    ('Seated Leg Curls'),
    ('Single Leg Seated Leg Curls'),
    ('Standing Leg Curls'),
    ('Single Leg Standing Leg Curls'),
    ('Hip Thrusts'),
    ('Single Leg Hip Thrust'),
    ('Romanian Deadlifts'),
    ('Stiff Leg Deadlifts'),
    ('Glute Focused Split Squats'),
    ('Glute Focused Lunges'),
    ('Hip Adductions'),
    ('Hip Abductions'),
    ('Donkey Kicks'),
    ('Side Kicks'),
--Chest
    ('Bench Press'),
    ('Incline Bench Press'),
    ('Decline Bench Press'),
    ('Dumbbell Press'),
    ('Incline Dumbbell Press'),
    ('Decline Dumbbell Press'),
    ('Machine Chest Press'),
    ('Incline Machine Chest Press'),
    ('Decline Machine Chest Press'),
    ('Dips'),
    ('Dumbbell Flies'),
    ('Peck Decks'),
    ('Low Cable Crossovers'),
    ('High Cable Crossovers'),
    ('Push-ups'),
    ('Incline Push-ups'),
    ('Decline Push-ups'),
--Triceps
    ('Narrow Grip Bench Press'),
    ('Skull Crushers'),
    ('Triceps Pushdown'),
    ('Single Arm Triceps Pushdown'),
    ('Triceps Kickbacks'),
    ('Single Arm Triceps Kickbacks'),
    ('Triceps Dips'),
    ('Dumbbell Overhead Triceps Extensions'),
    ('Single Arm Dumbbell Overhead Triceps Extensions'),
    ('Cable Overhead Triceps Extensions'),
    ('Single Arm Cable Overhead Triceps Extensions'),
--Back
    ('Deadlifts'),
    ('Lat Pulldowns'),
    ('Narrow Grip Lat Pulldowns'),
    ('Pull-Ups'),
    ('Seated Rows'),
    ('Narrow Grip Seated Rows'),
    ('Bent Over Rows'),
    ('Lat Pulldowns'),
    ('Good Mornings'),
    ('Back Extensions'),
    ('Single Arm Rows'),
    ('Single Arm Bent Over Rows'),
    ('Single Arm Pull Downs'),
--Biceps
    ('Hammer Curls'),
    ('Standing Barbell Curls'),
    ('Standing Dumbbell Curls'),
    ('Biceps Pull-Ups'),
    ('Biceps Pull-Downs'),
    ('Preacher Curls'),
    ('Single Arm Preacher Curls'),
    ('Seated Incline Curls'),
    ('Single Arm Seated Incline Curls'),
--Calves
    ('Standing Calf Raises'),
    ('Single Leg Standing Calf Raises'),
    ('Seated Calf Raises'),
    ('Single Leg Seated Calf Raises'),
    ('Leg Press Calf Raises'),
    ('Single Leg Leg Press Calf Raises'),
    ('Smith Machine Calf Raises'),
    ('Single Leg Smith Machine Calf Raises'),
--Shoulders
    ('Seated Barbell Shoulder Press'),
    ('Seated Dumbbell Shoulder Press'),
    ('Standing Barbell Shoulder Press'),
    ('Standing Dumbbell Shoulder Press'),
    ('Front Shoulder Raises'),
    ('Single Arm Front Shoulder Raises'),
    ('Lateral Raises'),
    ('Single Arm Lateral Raises'),
    ('Machine Rear Delt Flies'),
    ('Face Pulls'),
    ('Cable Rear Delt Flies'),
    ('Dumbbell Rear Delt Flies'),
    ('Upright Rows'),
    ('External Rotations'),
    ('Planche'),
    ('Bent Leg Planche'),
    ('Straddle Planche'),
    ('Single Leg Planche'),
--Forearms
    ('Forearm Dumbbell Curls'),
    ('Forearm Barbel Curls'),
    ('Forearm Dumbbell Extensions'),
    ('Forearm Barbel Extensions'),
--Abs
    ('Side Plank Raises'),
    ('Hanging Leg Raises'),
    ('Laying Leg Raises'),
    ('Crunches'),
    ('Dragon Flag'),
    ('Hanging Knee Raises');






--UPDATE exercise
--SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day' LIMIT 1),
--    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'Squats' LIMIT 1)
--WHERE id = 1;
--
--UPDATE exercise
--SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day1' LIMIT 1),
--    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'Squats' LIMIT 1)
--WHERE id = 2;
--
--UPDATE exercise
--SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day2' LIMIT 1),
--    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'Squats' LIMIT 1)
--WHERE id = 3;
--
--UPDATE exercise
--SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day3' LIMIT 1),
--    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'Squats' LIMIT 1)
--WHERE id = 4;
--
--UPDATE exercise
--SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day' LIMIT 1),
--    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'Leg Extensions' LIMIT 1)
--WHERE id = 5;
--
--UPDATE exercise
--SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day1' LIMIT 1),
--    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'Leg Extensions' LIMIT 1)
--WHERE id = 6;
--
--UPDATE exercise
--SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day2' LIMIT 1),
--    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'Leg Extensions' LIMIT 1)
--WHERE id = 7;
--
--UPDATE exercise
--SET workout_session_id = (SELECT id FROM workout_session WHERE workout_session_name = 'Leg day3' LIMIT 1),
--    exercise_type_id = (SELECT id FROM exercise_type WHERE name = 'Leg Extensions' LIMIT 1)
--WHERE id = 8;
--
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 1 LIMIT 1)
--WHERE id = 1;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 1 LIMIT 1)
--WHERE id = 2;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 2 LIMIT 1)
--WHERE id = 3;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 2 LIMIT 1)
--WHERE id = 4;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 3 LIMIT 1)
--WHERE id = 5;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 3 LIMIT 1)
--WHERE id = 6;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 4 LIMIT 1)
--WHERE id = 7;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 4 LIMIT 1)
--WHERE id = 8;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 5 LIMIT 1)
--WHERE id = 9;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 5 LIMIT 1)
--WHERE id = 10;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 6 LIMIT 1)
--WHERE id = 11;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 6 LIMIT 1)
--WHERE id = 12;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 7 LIMIT 1)
--WHERE id = 13;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 7 LIMIT 1)
--WHERE id = 14;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 8 LIMIT 1)
--WHERE id = 15;
--
--UPDATE exercise_set
--SET exercise_id = (SELECT id FROM exercise WHERE id = 8 LIMIT 1)
--WHERE id = 16;


