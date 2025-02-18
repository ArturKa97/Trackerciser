import React from "react";
import { Box, Typography } from "@mui/material";
import { FooterBox, FooterVersionBox } from "../styles/StyledComponents";

function Footer() {
  return (
    <FooterBox>
      <Box>
        <Typography variant="h6">
          © 2025 TRACKERCISER. ALL RIGHTS RESERVED.
        </Typography>
      </Box>
      <FooterVersionBox>
        <Typography variant="h6">Version: v1.0</Typography>
      </FooterVersionBox>
    </FooterBox>
  );
}

export default Footer;
