import React from "react";
import { BrowserRouter } from "react-router-dom";
import Header from "./components/Header";
import AppRoutes from "./routes/AppRoutes";
import store from "./store/store";

function App() {
  return (
    <BrowserRouter>
      <Provider store={store}>
        <Header />
        <AppRoutes />
      </Provider>
    </BrowserRouter>
  );
}

export default App;
