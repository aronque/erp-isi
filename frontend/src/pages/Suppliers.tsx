import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
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

const suppliers_endpoint = "http://localhost:8080/fornecedores";

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

    const getSuppliers = suppliers_endpoint;
    try {
      return axios.get(getSuppliers).then((response) => {
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

  const editSupplier = (supplier) => {
    setIsEditing(true);
    setSelectedInitialValues(supplier);
    setModalTitle("Editar Fornecedor - ID: " + supplier.id);
    const ModalWorkerButton = document.getElementById("ModalCRUD");
    ModalWorkerButton?.click();
  };

  const deleteSupplier = (supplier) => {

    const deleteSupplier = suppliers_endpoint + "/delete";

    try{
      axios.delete(deleteSupplier, {
        data: {
          id: supplier.id
        }
      });

      toast({
        title: "Fornecedor excluído com sucesso!",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
    } catch(err) {
      toast({
        title: "Erro! Não foi possível excluir o fornecedor!",
        status: "error",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
    }

  };

  const openInfoModal = (values) => {
    console.log("openInfoModal", values);

    const data = [];
    const titles = ["ID", "Nome", "Contato", "CNPJ", "Rua", "Número", "Bairro", "Cidade", "UF", "CEP"];
    const keys = ["id", "nome", "contato", "cnpj", "rua", "numero", "bairro", "cidade", "estado", "cep"];
    console.log("openInfoModal", values);

    titles.forEach((title, idx) => {
      if(idx < 4) {
        data.push({
          title,
          content: values[keys[idx]],
        });
      } else {
        //tratamento para exibir os dados do endereço
        data.push({
          title,
          content: values["endereco"][keys[idx]],
        });
      }
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

      var request = {
        nome: values.nome,
        cnpj: values.cnpj,
        contato: values.contato,
        endereco: {
          rua: values.rua,
          numero: values.numero,
          bairro: values.bairro,
          cidade: values.cidade,
          estado: values.estado,
          cep: values.cep
        }
      }
      const updateSupplier = suppliers_endpoint + "/update";

      try {
        axios.put(updateSupplier, request);

        toast({
          title: "Fornecedor editado com sucesso!",
          status: "success",
          duration: 3000,
          isClosable: true,
          position: "top",
        });
      } catch(err) {
        toast({
          title: "Erro! Não foi possível editar o cadastro do fornecedor!",
          status: "error",
          duration: 3000,
          isClosable: true,
          position: "top",
        });
      }

    } else {
    
      var request = {
        nome: values.nome,
        cnpj: values.cnpj,
        contato: values.contato,
        endereco: {
          rua: values.rua,
          numero: values.numero,
          bairro: values.bairro,
          cidade: values.cidade,
          estado: values.estado,
          cep: values.cep
        }
      }
      const insertSupplier = suppliers_endpoint + "/insert";
      
      try {
        axios.post(insertSupplier, request);

        toast({
          title: "Operação realizada com sucesso",
          status: "success",
          duration: 3000,
          isClosable: true,
          position: "top",
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
          button_text="Cadastrar Fornecedor"
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
                  supplier.nome.toLowerCase() ||
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
