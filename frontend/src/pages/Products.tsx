import React, { useContext, useEffect, useState } from "react";
import axios from 'axios';
import { BasicTable, BasicModal, InfoModal, ProductForm } from "../components";
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
import getSession from "../components/getSession";

const products_endpoint = "http://localhost:8080/produtos";

const ProductsPage: React.FC = () => {
  const user = JSON.parse(getSession());
  const { currentTheme } = useContext(ThemeContext);

  const toast = useToast();
  const [headers, setHeaders] = useState([
    {
      header_name: "Nome",
      header_key: "nome",
    },
    {
      header_name: "Fornecedor",
      header_key: "fornecedor",
    },
    {
      header_name: "Quantidade",
      header_key: "quantidade",
    },
  ]);
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [isEditing, setIsEditing] = useState(false);
  const [modalTitle, setModalTitle] = useState("Cadastrar Produto");
  const [selectedInitialValues, setSelectedInitialValues] = useState({});
  const [isOpenModalInfo, setIsOpenModalInfo] = useState(false);
  const [currentInfoModalData, setCurrentInfoModalData] = useState<
    InfoModalProps["data"]
  >([]);
  const [suppliers, setSuppliers] = useState([]);

  async function getProducts(suppliers?: any) {
    const res = await axios.get(products_endpoint);
    const filtered: any = await res.data;

    const products_with_suppliers = suppliers
      ? filtered.map((product: any) => {
          const supplier = suppliers.find((supplier: any) => {
            return supplier.id === product.fornecedor["id"];
          });
          return {
            ...product,
            fornecedor: supplier?.nome,
          };
        })
      : filtered;
    setData(products_with_suppliers);
    setFilteredData(products_with_suppliers);
    return filtered;
  }

  async function getSuppliers() {
    const workers_endpoint = "http://localhost:8080/fornecedores";
    
    const res = await axios.get(workers_endpoint);
    setSuppliers(res.data);
    return res.data;
  }

  const editProduct = (product) => {
    setIsEditing(true);
    console.log(product);
    setSelectedInitialValues(product);
    setModalTitle("Editar Produto - ID: " + product.id);
    const ModalProductButton = document.getElementById("ModalCRUD");
    ModalProductButton?.click();
  };

  const deleteProduct = (product) => {
    const deleteProductEndpoint = products_endpoint + "/delete";
    
    try {
      axios.delete(deleteProductEndpoint, {
        data: {
          id: product.id,
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
        } else if(res.data == 500) {
          toast({
            title: "Erro! Não foi possível deletar o produto!",
            status: "error",
            duration: 3000,
            isClosable: true,
            position: "top",
          });
        } else {
          toast({
            title: "Operação realizada com sucesso.",
            status: "success",
            duration: 3000,
            isClosable: true,
            position: "top",
          });
        }
      });
    } catch(err) {
      toast({
        title: "Erro! Não foi possível excluir o produto!",
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
    const titles = ["ID", "Nome", "Fornecedor", "Quantidade", "Preço"];
    const keys = ["id", "nome", "fornecedor", "quantidade", "preco"];
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
    setModalTitle("Cadastrar Produto");
    setSelectedInitialValues({});
  };

  const onSubmitProductForm = (values) => {
    console.log("onSubmitProductForm", values);
    if (isEditing) {

      const updateProductEndpoint = products_endpoint + "/update";

      var updateRequest = {
        id: values.id,
        nome: values.nome,
        fornecedor: {
          id: values.fornecedor_id
        },
        quantidade: values.quantidade,
        preco: values.preco,
        requestUser: {
          id: user['id']
        }
      }

      try {

        axios.put(updateProductEndpoint, updateRequest).then(res => {
          if(res.data == 405) {
            toast({
              title: "Você não tem privilégios suficiente para acessar esta funcionalidade",
              status: "error",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          } else if(res.data == 500) {
            toast({
              title: "Erro! Não foi possível modificar o produto!",
              status: "error",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          } else {
            toast({
              title: "Operação realizada com sucesso.",
              status: "success",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          }
        });
      } catch(err) {
        toast({
          title: "Erro! Não foi possível editar as informações do produto!",
          status: "error",
          duration: 3000,
          isClosable: true,
          position: "top",
        });
      }


    } else {

      const insertProductEndpoint = products_endpoint + "/insert";

      var request = {
        nome: values.nome,
        fornecedor: {
          id: values.fornecedor_id
        },
        quantidade: values.quantidade,
        preco: values.preco,
        requestUser: {
          id: user['id']
        }
      }

      try {

        axios.post(insertProductEndpoint, request).then(res => {
          if(res.data == 405) {
            toast({
              title: "Você não tem privilégios suficiente para acessar esta funcionalidade",
              status: "error",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          } else if(res.data == 500) {
            toast({
              title: "Erro! Não foi possível inserir o produto!",
              status: "error",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          } else {
            toast({
              title: "Operação realizada com sucesso.",
              status: "success",
              duration: 3000,
              isClosable: true,
              position: "top",
            });
          }
        });
      } catch (err) {
        toast({
          title: "Erro! Não foi possível inserir o produto!",
          status: "error",
          duration: 3000,
          isClosable: true,
          position: "top",
        });
      }

    }
    const CloseModalProductIcon = document.getElementById(
      "CloseModalProductIcon"
    );
    CloseModalProductIcon?.click();
  };

  useEffect(() => {
    async function init() {
      const suppliers = await getSuppliers();
      getProducts(suppliers);
    }

    const interval = setInterval(() => {
      if (window.innerWidth <= 768) {
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
            header_name: "Fornecedor",
            header_key: "fornecedor",
          },
          {
            header_name: "Quantidade",
            header_key: "quantidade",
          },
        ]);
      }
    }, 100);

    init();
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
        <Text fontSize={{ base: "2xl", md: "3xl" }}>Produtos</Text>
        <InfoModal
          is_open={isOpenModalInfo}
          closeInfoModal={closeInfoModal}
          modal_title="Detalhes do Produto"
          data={currentInfoModalData}
        />
        <BasicModal
          button_text="Cadastrar Produto"
          button_icon={<AddIcon mr={3} />}
          button_color_scheme={currentTheme === "light" ? "blue" : "facebook"}
          modal_title={modalTitle}
          is_editing={isEditing}
          onCloseModalWorker={onCloseModalWorker}
        >
          <ProductForm
            onSubmit={onSubmitProductForm}
            is_editing={isEditing}
            initial_values={selectedInitialValues}
            suppliers={suppliers}
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
              const filtered = data.filter((produto) => {
                return (
                  produto.nome.toLowerCase().includes(value.toLowerCase()) ||
                  produto.fornecedor.toLowerCase().includes(value.toLowerCase())
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
        onClickEdit={editProduct}
        onClickDelete={deleteProduct}
      />
    </Container>
  );
};

export default ProductsPage;
