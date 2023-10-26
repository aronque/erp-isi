import React, { useContext } from "react";
import {
  Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td,
  TableContainer,
  Box,
  Text,
  Popover,
  PopoverTrigger,
  PopoverContent,
  PopoverHeader,
  PopoverBody,
  PopoverCloseButton,
  Button,
  Flex,
} from "@chakra-ui/react";
import { DeleteIcon, EditIcon, HamburgerIcon } from "@chakra-ui/icons";
import { ThemeContext } from "../providers/ThemeProvider";

interface DefaultTableProps {
  headers?: { header_name: string; header_key: string }[];
  data?: any;
  table_color_scheme?: string;
  onClickTableRow: (row: any) => void;
  onClickEdit: (row: any) => void;
  onClickDelete: (row: any) => void;
}

export const BasicTable: React.FC<DefaultTableProps> = ({
  headers,
  data,
  table_color_scheme,
  onClickTableRow = () => {},
  onClickEdit = () => {},
  onClickDelete = () => {},
}) => {
  const { currentTheme } = useContext(ThemeContext);

  return (
    <Box borderWidth="1px" borderRadius="lg">
      <TableContainer>
        <Table variant="striped" colorScheme={table_color_scheme}>
          <Thead>
            <Tr>
              {headers?.map((header, idx) => (
                <Th
                  key={"header-" + idx}
                  style={{
                    color: "unset",
                    backgroundColor: "unset",
                    textTransform: "unset",
                  }}
                >
                  <Text>{header.header_name}</Text>
                </Th>
              ))}
              <Th
                style={{
                  color: "unset",
                  backgroundColor: "unset",
                  textTransform: "unset",
                }}
              >
                <Text>Ações</Text>
              </Th>
            </Tr>
          </Thead>
          <Tbody>
            {data?.map((row: any, i: number) => (
              <Tr key={"row-" + i}>
                {headers?.map((header, idx) => (
                  <Td
                    key={"row-header-" + idx}
                    onClick={() => onClickTableRow(row)}
                    style={{
                      cursor: "pointer",
                    }}
                  >
                    {row[header.header_key]}
                  </Td>
                ))}
                <Td>
                  <Popover>
                    <PopoverTrigger>
                      <Button variant="ghost">
                        <HamburgerIcon
                          id="hamburger-icon"
                          color={currentTheme === "dark" ? "white" : "black"}
                        />
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent
                      style={{
                        width: "fit-content",
                        backgroundColor:
                          currentTheme === "dark" ? "#1A202C" : "white",
                      }}
                    >
                      <PopoverCloseButton />
                      <PopoverHeader>Ações</PopoverHeader>
                      <PopoverBody>
                        <Flex gap={2}>
                          <Button
                            variant="outline"
                            colorScheme={
                              currentTheme === "dark" ? "gray" : "blue"
                            }
                            onClick={() => onClickEdit(row)}
                          >
                            <EditIcon
                              id="edit-icon"
                              color={
                                currentTheme === "dark" ? "white" : "black"
                              }
                            />
                          </Button>
                          <Button
                            variant="outline"
                            colorScheme={
                              currentTheme === "dark" ? "gray" : "blue"
                            }
                            onClick={() => onClickDelete(row)}
                          >
                            <DeleteIcon
                              id="delete-icon"
                              color={
                                currentTheme === "dark" ? "white" : "black"
                              }
                            />
                          </Button>
                        </Flex>
                      </PopoverBody>
                    </PopoverContent>
                  </Popover>
                </Td>
              </Tr>
            ))}
          </Tbody>
        </Table>
      </TableContainer>
    </Box>
  );
};
