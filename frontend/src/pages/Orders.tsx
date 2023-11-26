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

const orders_endpoint = "http://localhost:8080/pedidos";

const OrdersPage: React.FC = () => {
  const { currentTheme } = useContext(ThemeContext);
  const toast = useToast();
  const [headers, setHeaders] = useState([
    {
      header_name: "ID",
      header_key: "id",
    },
    {
      header_name: "Status",
      header_key: "status",
    },
    {
      header_name: "Tipo Pedido",
      header_key: "tipo",
    }
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

  async function getOrders() {

    const getSupplierOrders = orders_endpoint + "Fornecedor";
    const getProductsOrders = orders_endpoint + "Estoque";


    try {
      // await axios.get(getSupplierOrders).then((response) => {
      //   setData(response.data);
      //   setFilteredData(response.data);
      // });

      var resEstoque = await axios.get(getProductsOrders);
      var filteredEstoque: any = await resEstoque.data;

      var resFornecedor = await axios.get(getSupplierOrders);
      var filteredFornecedor: any = await resFornecedor.data;
      
      var filtered = [];

      var filteredFinalEstoque = filteredEstoque.map(obj => {
        return {
          ...obj,
          tipo: 'RETIRADA'
        };
      });
      filtered.push(...filteredFinalEstoque)

      var filteredFinalFornecedor = filteredFornecedor.map(obj => {
        return {
          ...obj,
          tipo: 'ENTRADA'
        };
      });
      filtered.push(...filteredFinalFornecedor)

      setData(filtered);
      setFilteredData(filtered);

      return filteredData;
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

    const deleteSupplier = orders_endpoint + "/delete";

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
    const titles = ["ID", "Status", "Tipo", "Data", "Usuário", "Fornecedor"];
    const keys = ["id", "status", "tipo", "data", "nome", "nome"];
    console.log("openInfoModal", values);

    titles.forEach((title, index) => {
      if(index < 4) {
        data.push({
          title,
          content: values[keys[index]]
        })
      } else {
        if(index < 5) { 
          if(values["usuario"] === null) {
            return;
          }
          data.push({
            title,
            content: values["usuario"][keys[index]]
          })
        } else {
          if(values["fornecedor"] === null) {
            return;
          }
          data.push({
            title,
            content: values["fornecedor"][keys[index]]
          })
        }
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
    setModalTitle("Cadastrar Pedido");
    setSelectedInitialValues({});
  };

  const onSubmitOrderForm = (values) => {
    console.log("onSubmitOrderForm", values);
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
      const updateOrder = orders_endpoint + "/update";

      try {
        axios.put(updateOrder, request);

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
      const insertOrder = orders_endpoint + "/insert";
      
      try {
        axios.post(insertOrder, request);

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
    getOrders();

    const interval = setInterval(() => {
      if (window.innerWidth < 768) {
        setHeaders([
          {
            header_name: "ID",
            header_key: "id",
          },
        ]);
      } else {
        setHeaders([
          {
            header_name: "ID",
            header_key: "id",            
          },
          {
            header_name: "Status",
            header_key: "status",
          },
          {
            header_name: "Tipo Pedido",
            header_key: "tipo"
          }
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
        <Text fontSize={{ base: "2xl", md: "3xl" }}>Pedidos</Text>
        <InfoModal
          is_open={isOpenModalInfo}
          closeInfoModal={closeInfoModal}
          modal_title="Detalhes do Pedido"
          data={currentInfoModalData}
        />
        <BasicModal
          button_text="Cadastrar Pedido"
          button_icon={<AddIcon mr={3} />}
          button_color_scheme={currentTheme === "light" ? "blue" : "facebook"}
          modal_title={modalTitle}
          is_editing={isEditing}
          onCloseModalWorker={onCloseModalWorker}
        >
          <SupplierForm
            onSubmit={onSubmitOrderForm}
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
              const filtered = [];
              filtered.push( data.filter((pedido) => {
                return (
                  pedido.id ||
                  pedido.status.toLowerCase().includes(value.toLowerCase())
                );
              }));
              console.log(filtered)
              filtered.map(obj => {
                if(obj.fornecedor === null) {
                  obj.tipo = 'RETIRADA';
                } else {
                  obj.tipo = 'ENTRADA';
                }

                return obj;
              })

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

export default OrdersPage;
