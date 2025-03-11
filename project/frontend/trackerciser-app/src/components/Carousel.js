import React, { useState } from "react";
import { Typography } from "@mui/material";
import {
  AlignStartGridBox,
  FeaturesCard,
  FeaturesCardContent,
  FeaturesList,
  FeaturesListItem,
  FlexRadioGroupBox,
  NextIconButton,
  PrevIconButton,
  StyledRadioButton,
} from "../styles/StyledComponents";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";

function Carousel() {
  const [activeIndex, setActiveIndex] = useState(0);
  const [direction, setDirection] = useState(1); // 1 for next, -1 for prev

  function handlePreviousCardClick() {
    setDirection(-1);
    setActiveIndex(
      (prevIndex) => (prevIndex - 1 + cardData.length) % cardData.length
    );
  }

  function handleNextCardClick() {
    setDirection(1);
    setActiveIndex((prevIndex) => (prevIndex + 1) % cardData.length);
  }

  const cardData = [
    {
      title: "TRACKING WORKOUTS",
      content: [
        "Create a workout session with a custom name and date",
        "Select exercises performed on that day.",
        "Input workout details: Sets, Reps, Weight, Rest and Duration",
        "Modify your workout history anytime, add, edit, or delete entries as needed.",
      ],
    },
    {
      title: "VISUAL PROGRESS",
      content: [
        "Avoid manually reviewing individual workout sessions and visually track your progress instead",
        "Access the Charts section to analyze progress over time.",
        "Select the date range along with the specific exercise and switch between different metrics",
        "View improvements through line charts, making it easy to analyze your training",
      ],
    },
    {
      title: "SETTING GOALS",
      content: [
        "Future update will allow users to set personal fitness goals",
        "Workout specific targets along with body composition goals",
        "Will be integrated with Charts for visual representation",
        "Ability to compare actual perfomance with the set goals",
      ],
    },
  ];

  return (
    <AlignStartGridBox>
      <FeaturesCard>
        <PrevIconButton
          aria-label="previous"
          onClick={() => handlePreviousCardClick()}
        >
          <ArrowBackIosNewIcon />
        </PrevIconButton>
        <FeaturesCardContent
          direction={direction}
          key={activeIndex}
          className={`slide-in ${direction === 1 ? "next" : "prev"}`}
        >
          <Typography variant="h2">{cardData[activeIndex].title}</Typography>
          <FeaturesList>
            {cardData[activeIndex].content.map((feature, index) => (
              <FeaturesListItem key={index}>{feature}</FeaturesListItem>
            ))}
          </FeaturesList>
        </FeaturesCardContent>
        <NextIconButton aria-label="next" onClick={() => handleNextCardClick()}>
          <ArrowForwardIosIcon />
        </NextIconButton>
      </FeaturesCard>
      <FlexRadioGroupBox>
        {cardData.map((_, index) => (
          <StyledRadioButton
            key={index}
            checked={activeIndex === index}
            onChange={() => setActiveIndex(index)}
            value={index}
          />
        ))}
      </FlexRadioGroupBox>
    </AlignStartGridBox>
  );
}

export default Carousel;
