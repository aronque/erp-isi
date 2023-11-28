import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import { BasicTable, BasicModal, WorkerForm, InfoModal } from "../components";
import {
  Text,
  Container,
  Flex,
  Spacer,
  Input,
  InputGroup,
  InputLeftElement,
} from "@chakra-ui/react";
import { AddIcon, Search2Icon } from "@chakra-ui/icons";
import { ThemeContext } from "../providers/ThemeProvider";
import { InfoModalProps } from "../components/InfoModal";
import { useToast } from "@chakra-ui/react";
import {Types} from "../components/utils/userTypes";
import getSession from "../components/getSession";

const users_endpoint = "http://localhost:8080/usuarios";

const WorkersPage: React.FC = () => {
  const user = JSON.parse(getSession());
  const { currentTheme } = useContext(ThemeContext);
  const toast = useToast();
  const [headers, setHeaders] = useState([
    {
      header_name: "Nome",
      header_key: "nome",
    },
    {
      header_name: "Email",
      header_key: "email",
    },
  ]);
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [isEditing, setIsEditing] = useState(false);
  const [modalTitle, setModalTitle] = useState("Cadastrar Usuário");
  const [selectedInitialValues, setSelectedInitialValues] = useState({});
  const [isOpenModalInfo, setIsOpenModalInfo] = useState(false);
  const [currentInfoModalData, setCurrentInfoModalData] = useState<
    InfoModalProps["data"]
  >([]);

  const getWorkers: () => Promise<any> = () => {

    const getUsers = users_endpoint;
    try {
      return axios.get(getUsers).then((response) => {
        setData(response.data);
        setFilteredData(response.data);
      });
    } catch(err) {
      toast ({
        title: "Erro!" + err,
        status: "error",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
    }
  };

  const editWorker = (worker) => {
    setIsEditing(true);
    setSelectedInitialValues(worker);
    setModalTitle("Editar Usuário - ID: " + worker.id);
    const ModalWorkerButton = document.getElementById("ModalCRUD");
    ModalWorkerButton?.click();
  };

  const deleteWorker = (worker) => {
    console.log("deleteWorker", worker);

    var request = {
      id: worker.id,
      requestUser: {
        id: user['id']
      }
    }

    try {
      axios.delete(users_endpoint + "/delete", {
        data: {
          id: worker.id,
          requestUser: {
            id: user['id']
          }
        }
      }).then(res => {
        if(res.data == 405) {
          toast({
            title: "Você não tem privilégios suficiente para acessar esta funcionalidade",
            status: "error",
            duration: 3000,
            isClosable: true,
            position: "top",
          });
        } else {
          toast({
            title: "Operação realizada com sucesso",
            status: "success",
            duration: 3000,
            isClosable: true,
            position: "top",
          });
        }
      });
    } catch(err) {

    }
  };

  const openInfoModal = (values) => {
    console.log("openInfoModal", values);
    const data = [];
    const titles = ["ID", "Nome", "Email", "Tipo"];
    const keys = ["id", "nome", "email", "tipo"];
    titles.forEach((title, idx) => {
      data.push({
        title,
        content: values[keys[idx]],
      });
    });
    setCurrentInfoModalData(data);
    setIsOpenModalInfo(true);
  };

  const closeInfoModal = () => {
    setIsOpenModalInfo(false);
  };

  const onCloseModalWorker = () => {
    setIsEditing(false);
    setModalTitle("Cadastrar Usuário");
    setSelectedInitialValues({});
  };

  const onSubmitWorkerForm = (values) => {
    console.log("onSubmitWorkerForm", values);
    if (isEditing) {

      var request = {
        id: values.id,
        nome: values.nome,
        email: values.email,
        tipo: values.tipo,
        requestUser: {
          id: user['id']
        }
      }
      const updateUserEndpoint = users_endpoint + "/update";


      try {
        axios.put(updateUserEndpoint, request).then((res) => {
          if(res.data == 405) {
            toast({
              title: "Você não tem privilégios suficiente para acessar esta funcionalidade",
              status: "error",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          } else {
            toast({
              title: "Operação realizada com sucesso",
              status: "success",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          }
        });
      } catch(err) {
        toast({
          title: "Erro! Operação não realizada.",
          status: "error",
          duration: 3000,
          isClosable: true,
          position: "top",
        });
      }
    } else {

      var requestInsert = {
        nome: values.nome,
        email: values.email,
        senha: values.senha,
        tipo: values.tipo,
        requestUser: {
          id: user['id']
        }
      }
      const insertUserEndpoint = users_endpoint + "/insert";

      try {
        axios.post(insertUserEndpoint, requestInsert).then((res) => {
          if(res.data == 405) {
            toast({
              title: "Você não tem privilégios suficiente para acessar esta funcionalidade",
              status: "error",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          } else {
            toast({
              title: "Operação realizada com sucesso",
              status: "success",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          }
        });
      } catch(err) {
        toast({
          title: "Erro! Operação não realizada.",
          status: "error",
          duration: 3000,
          isClosable: true,
          position: "top",
        });
      }

    }
    const CloseModalWorkerIcon = document.getElementById(
      "CloseModalWorkerIcon"
    );
    CloseModalWorkerIcon?.click();
  };

  useEffect(() => {
    getWorkers();
    const interval = setInterval(() => {
      if (window.innerWidth < 768) {
        setHeaders([
          {
            header_name: "Nome",
            header_key: "nome",
          },
        ]);
      } else {
        setHeaders([
          {
            header_name: "Nome",
            header_key: "nome",
          },
          {
            header_name: "Email",
            header_key: "email",
          },
        ]);
      }
    }, 100);

    return () => clearInterval(interval);
  }, []);

  return (
    <Container maxWidth={1000}>
      <Spacer height={4} />
      <Flex
        justifyContent={{
          base: "center",
          sm: "space-between",
        }}
        alignItems="center"
        flexWrap="wrap"
        gap={3}
      >
        <Text fontSize={{ base: "2xl", md: "3xl" }}>Usuários</Text>
        <InfoModal
          is_open={isOpenModalInfo}
          closeInfoModal={closeInfoModal}
          modal_title="Detalhes do Uusário"
          data={currentInfoModalData}
        />
        <BasicModal
          button_text="Cadastrar Usuário"
          button_icon={<AddIcon mr={3} />}
          button_color_scheme={currentTheme === "light" ? "blue" : "facebook"}
          modal_title={modalTitle}
          is_editing={isEditing}
          onCloseModalWorker={onCloseModalWorker}
        >
          <WorkerForm
            onSubmit={onSubmitWorkerForm}
            is_editing={isEditing}
            initial_values={selectedInitialValues}
          />
        </BasicModal>
      </Flex>
      {/* flex to the input search */}
      <Spacer height={4} />
      <Flex
        justifyContent={{
          base: "center",
          sm: "flex-end",
        }}
      >
        <InputGroup
          style={{
            width: "200px",
          }}
        >
          <InputLeftElement pointerEvents="none">
            <Search2Icon color="gray.300" />
          </InputLeftElement>
          <Input
            placeholder="Pesquisar..."
            onChange={(e) => {
              const value = e.target.value;
              const filtered = data.filter((worker) => {
                return (
                  worker.nome.toLowerCase().includes(value.toLowerCase()) ||
                  worker.email.toLowerCase().includes(value.toLowerCase())
                );
              });
              setFilteredData(filtered);
            }}
            colorScheme={currentTheme === "light" ? "blackAlpha" : "facebook"}
          />
        </InputGroup>
      </Flex>
      <Spacer height={4} />
      <BasicTable
        headers={headers}
        data={filteredData}
        table_color_scheme={
          currentTheme === "light" ? "blackAlpha" : "whiteAlpha"
        }
        onClickTableRow={openInfoModal}
        onClickEdit={editWorker}
        onClickDelete={deleteWorker}
      />
    </Container>
  );
};

export default WorkersPage;
