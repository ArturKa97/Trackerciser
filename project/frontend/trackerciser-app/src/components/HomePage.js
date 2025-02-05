import { Box, Typography } from "@mui/material";
import { MainContainer, TwoColumnGridBox } from "../styles/StyledComponents";
import Carousel from "./Carousel";

function HomePage() {
  return (
    <MainContainer>
      <TwoColumnGridBox>
        <Box>
          <Typography variant="h1">
            SIMPLIFY YOUR WORKOUT AND PROGRESS TRACKING
          </Typography>
        </Box>
        <Carousel />
      </TwoColumnGridBox>
    </MainContainer>
  );
}

export default HomePage;
