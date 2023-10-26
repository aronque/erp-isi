import React, { useContext, useEffect, useState } from "react";
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

const WorkersPage: React.FC = () => {
  const { currentTheme } = useContext(ThemeContext);
  const toast = useToast();
  const [headers, setHeaders] = useState([
    {
      header_name: "Nome",
      header_key: "name",
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
    const workers_endpoint = "usuarios.json";
    return fetch(workers_endpoint)
      .then((data) => data.json())
      .then((data) => {
        setData(data.usuarios);
        setFilteredData(data.usuarios);
        return data.usuarios;
      })
      .catch((error) => console.error("Error fetching data:", error));
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
    toast({
      title: "Usuário excluído com sucesso!",
      status: "success",
      duration: 3000,
      isClosable: true,
      position: "top",
    });
  };

  const openInfoModal = (values) => {
    console.log("openInfoModal", values);
    const data = [];
    const titles = ["ID", "Nome", "Email", "Apelido"];
    const keys = ["id", "name", "email", "username"];
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
      toast({
        title: "Usuário editado com sucesso!",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
    } else {
      toast({
        title: "Usuário cadastrado com sucesso!",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
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
            header_key: "name",
          },
        ]);
      } else {
        setHeaders([
          {
            header_name: "Nome",
            header_key: "name",
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
                  worker.name.toLowerCase().includes(value.toLowerCase()) ||
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
