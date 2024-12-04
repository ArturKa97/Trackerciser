import React from "react";
import { Box, Typography } from "@mui/material";

function Footer() {
  return (
    <Box
      sx={{
        backgroundColor: "primary.main",
        color: "primary.contrastText",
        textAlign: "center",
        py: 1.5,
        height: "8vh"
      }}
    >
      <Typography variant="h7">
        © 2024 Trackerciser. All rights reserved.
      </Typography>
    </Box>
  );
}

export default Footer;
