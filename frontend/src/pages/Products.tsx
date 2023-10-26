import React, { useContext, useEffect, useState } from "react";
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

const ProductsPage: React.FC = () => {
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
    const products_endpoint = "produtos.json";
    const res = await fetch(products_endpoint);
    const json: any = await res.json();

    const products_with_suppliers = suppliers
      ? json.produtos.map((product: any) => {
          const supplier = suppliers.find((supplier: any) => {
            return supplier.id === product.fornecedor_id;
          });
          return {
            ...product,
            fornecedor: supplier?.nome,
          };
        })
      : json.produtos;
    setData(products_with_suppliers);
    setFilteredData(products_with_suppliers);
    return json.produtos;
  }

  async function getSuppliers() {
    const workers_endpoint = "fornecedores.json";
    const res = await fetch(workers_endpoint);
    const json = await res.json();
    setSuppliers(json.fornecedores);
    return json.fornecedores;
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
    toast({
      title: "Produto excluído com sucesso!",
      status: "success",
      duration: 3000,
      isClosable: true,
      position: "top",
    });
  };

  const openInfoModal = (values) => {
    console.log("openInfoModal", values);
    const data = [];
    const titles = ["ID", "Nome", "Fornecedor", "Quantidade"];
    const keys = ["id", "nome", "fornecedor", "quantidade"];
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
      toast({
        title: "Produto editado com sucesso!",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
    } else {
      toast({
        title: "Produto cadastrado com sucesso!",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
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
