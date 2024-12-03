import React from "react";
import { BrowserRouter } from "react-router-dom";
import Header from "./components/Header";
import AppRoutes from "./routes/AppRoutes";
import store from "./store/store";
import {Provider} from "react-redux";
import { initBackendApiClient } from "./api";
import { Container } from "@mui/material";


initBackendApiClient(store)

function App() {
  return (
    <BrowserRouter>
      <Provider store={store}>
        <Container sx={{
          display: "flex",
          flexDirection: "column"

        }}>
        <Header />
        <AppRoutes />
        </Container>
      </Provider>
    </BrowserRouter>
  );
}

export default App;
