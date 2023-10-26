import React, { useState, ReactNode } from "react";
import { Theme } from "../types";

type ThemeContextType = {
  currentTheme: Theme;
  setTheme: (theme?: Theme) => void;
};

export const ThemeContext = React.createContext<ThemeContextType>(
  {} as ThemeContextType
);

interface ThemeProviderProps {
  children: ReactNode;
}

export const ThemeProvider: React.FC<ThemeProviderProps> = ({ children }) => {
  const [currentTheme, setCurrentTheme] = useState<Theme>("light");
  const setTheme = (theme?: Theme) => {
    if (theme) {
      setCurrentTheme(theme);
    } else {
      setCurrentTheme(currentTheme === "light" ? "dark" : "light");
    }
  };

  return (
    <ThemeContext.Provider value={{ currentTheme, setTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};
