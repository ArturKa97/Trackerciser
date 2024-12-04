import React from "react";
import { BrowserRouter } from "react-router-dom";
import Header from "./components/Header";
import AppRoutes from "./routes/AppRoutes";
import store from "./store/store";
import { Provider } from "react-redux";
import { initBackendApiClient } from "./api";
import { Box } from "@mui/material";
import Footer from "./components/Footer";

initBackendApiClient(store);

function App() {
  return (
    <BrowserRouter>
      <Provider store={store}>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            height: "100vh",
          }}
        >
          <Box sx={{ flexShrink: 0 }}>
            <Header />
          </Box>
          <Box
            component="main"
            sx={{
              width: 0.7,
              flexGrow: 1,
              overflowY: "auto",
              justifyContent: "center",
              padding: 2,
              margin: "0 auto",
            }}
          >
            <AppRoutes />
          </Box>
          <Box sx={{ flexShrink: 0 }}>
            <Footer />
          </Box>
        </Box>
      </Provider>
    </BrowserRouter>
  );
}

export default App;
