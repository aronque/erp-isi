import { createContext, useContext, ReactNode, useState, useEffect} from 'react';
import axios from 'axios';
import { useStateWithCallbackLazy } from 'use-state-with-callback';

interface User {
  id: any,
  email: String,
  password: String;
  // outros dados do usuário, se necessário
}

interface AuthContextProps {
  user: User | null;
  login: (user: User) => void;
  logout: () => void;

}

export const AuthContext = createContext<AuthContextProps | undefined>(undefined);

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [user, setUser] = useStateWithCallbackLazy<User>(null);
  const [users, setUsers] = useState([]);

  useEffect(() => {
    getUsers();

    const localUser = localStorage.getItem("token_user")

    if(users) {
      const hasUser = users?.filter((user) => {
        return user.email === localUser['email'];
      })

      if(hasUser) setUser(hasUser[0], old => {
        console.log("Old:" + old)
      })
    }

  }, [])

  const getUsers: () => Promise<any> = () => {
    
    const loginEndpoint = "http://localhost:8080/usuarios"; 

    try {
      return axios.get(loginEndpoint).then((res) => {
        setUsers(res.data)

        // if (user) {
        //   const hasUser = res.data.find((u) => u.email === user.email);
        //   if (hasUser) {
        //     setUser({
        //       email: hasUser.email,
        //       id: hasUser.id,
        //       password: hasUser.senha,
        //     });
        //   }
        // }
      });
      
    } catch(err) {

    }
  }


  const login = (userData: User) => {
    
    const hasUser = users.filter((user) => user.email === userData.email);
    
    if (hasUser?.length) {
      if (hasUser[0].email === userData.email && hasUser[0].senha === userData.password) {
        console.log("vai setar usuario")
        const token = Math.random().toString(36).substring(2);
        localStorage.setItem("token_user", JSON.stringify({
          token: token,
          id: hasUser[0]['id'],
          email: userData.email,
          password: userData.password
        }))

        setUser({
          id: hasUser[0]['id'],
          email: userData.email,
          password: userData.password
        }, newValue => {
          console.log("New: " + newValue)
        });

        return true;
      } else {
        return false;
      }
    } else {
      return null;
    }

    // setUser(userData);
    // console.log("outro" + user)

    // setUser((prevUser) => {
    //     console.log("antigo" + prevUser); // Este log deve mostrar o valor anterior do usuário
    //     console.log("novo" + userData); // Este log deve mostrar o novo valor do usuário
    //     return userData;
    // });
  };


  const logout = () => {

    localStorage.setItem("token_user", null);
    setUser(null, newValue => {
      console.log("New: " + newValue)
    });
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