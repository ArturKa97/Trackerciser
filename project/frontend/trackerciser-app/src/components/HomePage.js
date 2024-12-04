import { Box, Container, Paper, Typography } from "@mui/material";

function HomePage() {
  return (
    <Container
      sx={{
        mx: { xs: 2, md: 4 }, // Responsive margin
        my: 2,
        maxWidth: "xl", // Restrict maximum width
      }}
    >
      <Box
        sx={{
          display: "flex",
          flexDirection: { xs: "column", md: "row" },
          justifyContent: "space-between",
          gap: 4,
        }}
      >
        <Paper
          elevation={4}
          sx={{
            m: 1,
            backgroundColor: "primary.light",
            width: { xs: 1, md: 0.3 },
            overflow: "hidden",
            p: 1,
          }}
        >
          <Typography
            variant="h5"
            sx={{
              textAlign: "center",
              m: 1,
              color: "primary.contrastText",
            }}
          >
            Tracking Workouts
          </Typography>
          <Typography
            variant="h7"
            sx={{
              textAlign: "center",
              color: "primary.contrastText",
              wordWrap: "break-word",
            }}
          >
            Ability to track your workouts, just simply create a workout session
            with a name and date of your choice, select the exercises that you
            did on that specific day and input your data on how many sets,
            repetitions you did with what weight and how much rest you had in
            between sets or exercises, also you can add a time for how long you
            did the specific set, usefull for the static exercises that just
            need to be held for time in a fixed position. Then you always have
            an ability to add, delete, or edit anything included.
          </Typography>
        </Paper>
        <Paper
          elevation={4}
          sx={{
            m: 1,
            backgroundColor: "primary.light",
            width: { xs: 1, md: 0.3 },
            overflow: "hidden",
            p: 1,
          }}
        >
          <Typography
            variant="h5"
            sx={{
              textAlign: "center",
              m: 1,
              color: "primary.contrastText",
            }}
          >
            Progress visualization
          </Typography>
          <Typography
            variant="h7"
            sx={{
              textAlign: "center",
              color: "primary.contrastText",
              wordWrap: "break-word",
            }}
          >
            Tracking your progress by going through each workout session one by
            one and just looking at the specific exercise and specific numbers
            would take lots of time and effort. Thats why you have an ability to
            visually see your desired progress by simply selecting the "Charts"
            section, then selecting your desired date interval and finally you
            have an ability to select any exercise you did in the specified time
            period and switch between all the trackable data "Sets, Reps,
            Weight, Rest, Duration" and see the progress visually represented by
            a line chart, which shows the difference between your workouts on
            each date in the selected period.
          </Typography>
        </Paper>
        <Paper
          elevation={4}
          sx={{
            m: 1,
            backgroundColor: "primary.light",
            width: { xs: 1, md: 0.3 },
            overflow: "hidden",
            p: 1,
          }}
        >
          <Typography
            variant="h5"
            sx={{
              textAlign: "center",
              m: 1,
              color: "primary.contrastText",
            }}
          >
            Setting goals
          </Typography>
          <Typography
            variant="h7"
            sx={{
              textAlign: "center",
              color: "primary.contrastText",
              wordWrap: "break-word",
            }}
          >
            Future goals: In the future the ability to set individual goals for
            each user will be implemented. Whether it would be a goal related to
            workouts or something along the lines of losing weight. This will
            probably be integrated with "Charts", to see how far or how close
            you are to your set goals, and maybe if you managed to beat them in
            the time that you desired.
          </Typography>
        </Paper>
      </Box>
    </Container>
  );
}

export default HomePage;
