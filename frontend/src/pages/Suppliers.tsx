import React, { useContext, useEffect, useState } from "react";
import { BasicTable, BasicModal, SupplierForm, InfoModal } from "../components";
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

const SuppliersPage: React.FC = () => {
  const { currentTheme } = useContext(ThemeContext);
  const toast = useToast();
  const [headers, setHeaders] = useState([
    {
      header_name: "Nome",
      header_key: "nome",
    },
    {
      header_name: "Contato",
      header_key: "contato",
    },
  ]);
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [isEditing, setIsEditing] = useState(false);
  const [modalTitle, setModalTitle] = useState("Cadastrar Fornecedor");
  const [selectedInitialValues, setSelectedInitialValues] = useState({});
  const [isOpenModalInfo, setIsOpenModalInfo] = useState(false);
  const [currentInfoModalData, setCurrentInfoModalData] = useState<
    InfoModalProps["data"]
  >([]);

  const getSuppliers: () => Promise<any> = () => {
    const suppliers_endpoint = "fornecedores.json";
    return fetch(suppliers_endpoint)
      .then((data) => data.json())
      .then((data) => {
        setData(data.fornecedores);
        setFilteredData(data.fornecedores);
        return data.fornecedores;
      })
      .catch((error) => console.error("Error fetching data:", error));
  };

  const editSupplier = (supplier) => {
    setIsEditing(true);
    setSelectedInitialValues(supplier);
    setModalTitle("Editar Fornecedor - ID: " + supplier.id);
    const ModalWorkerButton = document.getElementById("ModalCRUD");
    ModalWorkerButton?.click();
  };

  const deleteSupplier = (supplier) => {
    console.log("deleteSupplier", supplier);
    toast({
      title: "Fornecedor excluído com sucesso!",
      status: "success",
      duration: 3000,
      isClosable: true,
      position: "top",
    });
  };

  const openInfoModal = (values) => {
    console.log("openInfoModal", values);
    const data = [];
    const titles = ["ID", "Nome", "Contato", "CNPJ", "Endereço ID"];
    const keys = ["id", "nome", "contato", "cnpj", "id_endereco"];
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
    setModalTitle("Cadastrar Fornecedor");
    setSelectedInitialValues({});
  };

  const onSubmitSupplierForm = (values) => {
    console.log("onSubmitSupplierForm", values);
    if (isEditing) {
      toast({
        title: "Fornecedor editado com sucesso!",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
    } else {
      toast({
        title: "Fornecedor cadastrado com sucesso!",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
    }
    const CloseModalSupplierIcon = document.getElementById(
      "CloseModalSupplierIcon"
    );
    CloseModalSupplierIcon?.click();
  };

  useEffect(() => {
    getSuppliers();

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
            header_name: "Contato",
            header_key: "contato",
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
        <Text fontSize={{ base: "2xl", md: "3xl" }}>Fornecedores</Text>
        <InfoModal
          is_open={isOpenModalInfo}
          closeInfoModal={closeInfoModal}
          modal_title="Detalhes do Fornecedor"
          data={currentInfoModalData}
        />
        <BasicModal
          button_text="Cadastrar Forncedor"
          button_icon={<AddIcon mr={3} />}
          button_color_scheme={currentTheme === "light" ? "blue" : "facebook"}
          modal_title={modalTitle}
          is_editing={isEditing}
          onCloseModalWorker={onCloseModalWorker}
        >
          <SupplierForm
            onSubmit={onSubmitSupplierForm}
            is_editing={isEditing}
            initial_values={selectedInitialValues}
          />
        </BasicModal>
      </Flex>
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
              const filtered = data.filter((supplier) => {
                return (
                  supplier.nome.toLowerCase().includes(value.toLowerCase()) ||
                  supplier.contato.toLowerCase().includes(value.toLowerCase())
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
        onClickEdit={editSupplier}
        onClickDelete={deleteSupplier}
      />
    </Container>
  );
};

export default SuppliersPage;
