import React, { useContext, useEffect } from "react";
import {
  Sidebar,
  Menu,
  MenuItem,
  SubMenu,
  menuClasses,
  MenuItemStyles,
} from "react-pro-sidebar";
import { ThemeContext } from "../providers/ThemeProvider";
import {
  DiamondIcon,
  BarChartIcon,
  GlobalIcon,
  InkBottleIcon,
  ShoppingCartIcon,
} from "./icons";

import { SidebarHeader } from "./SidebarHeader";
import { MenuLink } from "./SidebarComponents";
import { useLocation } from "react-router-dom";

const themes = {
  light: {
    sidebar: {
      backgroundColor: "#ffffff",
      color: "#607489",
    },
    menu: {
      menuContent: "#fbfcfd",
      icon: "#0098e5",
      hover: {
        backgroundColor: "#c5e4ff",
        color: "#44596e",
      },
      disabled: {
        color: "#9fb6cf",
      },
    },
  },
  dark: {
    sidebar: {
      backgroundColor: "#0b2948",
      color: "#8ba1b7",
    },
    menu: {
      menuContent: "#082440",
      icon: "#59d0ff",
      hover: {
        backgroundColor: "#00458b",
        color: "#b6c8d9",
      },
      disabled: {
        color: "#3e5e7e",
      },
    },
  },
};

const hexToRgba = (hex: string, alpha: number) => {
  const r = parseInt(hex.slice(1, 3), 16);
  const g = parseInt(hex.slice(3, 5), 16);
  const b = parseInt(hex.slice(5, 7), 16);

  return `rgba(${r}, ${g}, ${b}, ${alpha})`;
};

export const MainSidebar: React.FC = () => {
  const { currentTheme, setTheme } = useContext(ThemeContext);
  const [collapsed, setCollapsed] = React.useState(false);

  const location = useLocation();
  const isRestrictedPath =
    location.pathname.endsWith("/entrar") ||
    location.pathname.endsWith("/registrar");

  useEffect(() => {
    const interval = setInterval(() => {
      if (window.innerWidth < 768) {
        setCollapsed(true);
      }
    }, 1000);
    return () => clearInterval(interval);
  }, []);

  if (isRestrictedPath) {
    return null;
  }

  const menuItemStyles: MenuItemStyles = {
    root: {
      fontSize: "13px",
      fontWeight: 400,
    },
    icon: {
      color: themes[currentTheme].menu.icon,
      [`&.${menuClasses.disabled}`]: {
        color: themes[currentTheme].menu.disabled.color,
      },
    },
    SubMenuExpandIcon: {
      color: "#b6b7b9",
    },
    subMenuContent: ({ level }) => ({
      backgroundColor:
        level === 0
          ? hexToRgba(themes[currentTheme].menu.menuContent, 1)
          : "transparent",
    }),
    button: {
      [`&.${menuClasses.disabled}`]: {
        color: themes[currentTheme].menu.disabled.color,
      },
      "&:hover": {
        backgroundColor: hexToRgba(
          themes[currentTheme].menu.hover.backgroundColor,
          1
        ),
        color: themes[currentTheme].menu.hover.color,
      },
    },
    label: ({ open }) => ({
      fontWeight: open ? 600 : undefined,
    }),
  };

  return (
    <div
      style={{
        display: "flex",
        height: "100%",
        minHeight: "100vh",
        position: "sticky",
        top: 0,
        bottom: 0,
        zIndex: 1000,
      }}
    >
      <Sidebar
        collapsed={collapsed}
        backgroundColor={hexToRgba(
          themes[currentTheme].sidebar.backgroundColor,
          1
        )}
        rootStyles={{
          color: themes[currentTheme].sidebar.color,
        }}
      >
        <div
          style={{ display: "flex", flexDirection: "column", height: "100%" }}
        >
          <SidebarHeader
            collapsed={collapsed}
            style={{
              marginBottom: "24px",
              marginTop: "16px",
              cursor: "pointer",
            }}
            onClick={() => {
              if (window.innerWidth > 768) {
                setCollapsed(!collapsed);
              }
            }}
          />
          <div style={{ flex: 1, marginBottom: "32px" }}>
            <Menu menuItemStyles={menuItemStyles}>
              <MenuLink to="/">
                <MenuItem icon={<DiamondIcon />}>Início / Gráficos</MenuItem>
              </MenuLink>
              <MenuLink to="usuarios">
                <MenuItem icon={<BarChartIcon />}>Usuários</MenuItem>
              </MenuLink>
              <MenuLink to="fornecedores">
                <MenuItem icon={<GlobalIcon />}>Fornecedores</MenuItem>
              </MenuLink>
              <MenuLink to="produtos">
                <MenuItem icon={<ShoppingCartIcon />}>Estoque</MenuItem>
              </MenuLink>
              <MenuLink to="pedidos">
                <MenuItem icon={<ShoppingCartIcon />}>Pedidos</MenuItem>
              </MenuLink>
              <SubMenu label="Tema" icon={<InkBottleIcon />}>
                <MenuItem onClick={() => setTheme("dark")}> Escuro</MenuItem>
                <MenuItem onClick={() => setTheme("light")}> Claro</MenuItem>
              </SubMenu>
            </Menu>
          </div>
        </div>
      </Sidebar>
    </div>
  );
};
