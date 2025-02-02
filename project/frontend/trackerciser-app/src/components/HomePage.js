import { Box, Typography } from "@mui/material";
import {
  AlignStartGridBox,
  FeaturesCard,
  FeaturesCardContent,
  FeaturesTypography,
  MainContainer,
  TwoColumnGridBox,
} from "../styles/StyledComponents";

function HomePage() {
  return (
    <MainContainer>
      <TwoColumnGridBox>
        <Box>
          <Typography variant="h1">
            SIMPLIFY YOUR WORKOUT AND PROGRESS TRACKING
          </Typography>
        </Box>
        <AlignStartGridBox>
          <FeaturesCard>
            <FeaturesCardContent>
              <Typography variant="h2">TRACKING WORKOUTS</Typography>
              <FeaturesTypography>
                Ability to track your workouts, just simply create a workout
                session with a name and date of your choice, select the
                exercises that you did on that specific day and input your data
                on how many sets, repetitions you did with what weight and how
                much rest you had in between sets or exercises, also you can add
                a time for how long you did the specific set, usefull for the
                static exercises that just need to be held for time in a fixed
                position. Then you always have an ability to add, delete, or
                edit anything included.
              </FeaturesTypography>
            </FeaturesCardContent>
          </FeaturesCard>
        </AlignStartGridBox>
      </TwoColumnGridBox>
    </MainContainer>
  );
}

export default HomePage;
