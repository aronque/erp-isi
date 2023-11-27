import { createContext, useContext, ReactNode, useState } from 'react';

interface User {
  id: any;
  // outros dados do usuário, se necessário
}

interface AuthContextProps {
  user: User | null;
  login: (user: User) => void;
  logout: () => void;

}

const AuthContext = createContext<AuthContextProps | undefined>(undefined);

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);

  const login = (userData: User) => {
    setUser(userData);
    console.log("outro" + user.id)

    setUser((prevUser) => {
        console.log("antigo" + prevUser); // Este log deve mostrar o valor anterior do usuário
        console.log("novo" + userData); // Este log deve mostrar o novo valor do usuário
        return userData;
    });
  };


  const logout = () => {
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};