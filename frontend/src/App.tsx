import styled from "@emotion/styled";
import { MainSidebar } from "./components/MainSidebar";

import { BrowserRouter, Routes, Route } from "react-router-dom";

import { Suppliers, Home, Products, Workers, Login, Register, Report } from "./pages";
import { ThemeContext } from "./providers/ThemeProvider";
import { useContext } from "react";
import { Theme } from "./types";

const MainContainer = styled.div<{ theme?: Theme }>`
  display: flex;
  justify-content: center;
  min-height: 100vh;
  background-color: ${({ theme }) =>
    theme === "dark" ? "#484848" : "#FAF3F0"};
  color: ${({ theme }) => (theme === "dark" ? "#fbfcfd" : "#44596e")};
  input::placeholder {
    color: ${({ theme }) => (theme === "dark" ? "#dddddd" : "#9a9ea2")};
  }
`;

function App() {
  const { currentTheme } = useContext(ThemeContext);
  return (
    <MainContainer className="global-container" theme={currentTheme}>
      <BrowserRouter>
        <MainSidebar />
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/usuarios" element={<Workers />} />
          <Route path="/fornecedores" element={<Suppliers />} />
          <Route path="/produtos" element={<Products />} />
          <Route path="/home" element={<Home />} />
          <Route path="/registrar" element={<Register />} />
          <Route path="/relatorios" element={<Report />} />
        </Routes>
      </BrowserRouter>
    </MainContainer>
  );
}

export default App;
