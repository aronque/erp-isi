import styled from "@emotion/styled";
import React from "react";

interface SidebarHeaderProps extends React.HTMLAttributes<HTMLDivElement> {
  children?: React.ReactNode;
  collapsed?: boolean;
}

const StyledSidebarHeader = styled.div`
  height: 64px;
  min-height: 64px;
  display: flex;
  align-items: center;
  padding: 0 20px;

  > div {
    width: 100%;
    overflow: hidden;
  }
`;

const StyledLogo = styled.div<{ rtl?: boolean }>`
  padding: 0 8px;
  height: 35px;
  min-height: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
  color: white;
  font-size: 24px;
  font-weight: 700;
  background-color: #009fdb;
  background: linear-gradient(45deg, rgb(21 87 205) 0%, rgb(90 225 255) 100%);
`;

export const SidebarHeader: React.FC<SidebarHeaderProps> = ({
  children,
  collapsed,
  ...rest
}) => {
  return (
    <StyledSidebarHeader {...rest}>
      <div style={{ display: "flex", alignItems: "center" }}>
        <StyledLogo>{collapsed ? "D" : "Dashboard"}</StyledLogo>
      </div>
    </StyledSidebarHeader>
  );
};
