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
import { OrderForm } from "../components/OrderForm";
import getSession from "../components/getSession";

const orders_endpoint = "http://localhost:8080/pedidos";
const suppliersOrders = "Fornecedor";
const productsOrders = "Estoque";


const OrdersPage: React.FC = () => {

  const user = JSON.parse(getSession());
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

  const [products, setProducts] = useState([]);
  const [suppliers, setSuppliers] = useState([]);


  const [productsFetched, setProductsFetched] = useState(false);
  const [suppliersFetched, setSuppliersFetched] = useState(false);

  const getProducts: () => Promise<any> = () => {
    const productsEndpoint = "http://localhost:8080/produtos";

    // Verifica se os dados já foram buscados
    if (productsFetched) {
      return Promise.resolve();
    }

    return axios.get(productsEndpoint).then((response) => {
      setProducts(response.data);
      setProductsFetched(true); // Atualiza a flag indicando que os dados foram buscados
    });
    
  };

  const getSuppliers: () => Promise<any> = () => {
    const suppliersEndpoint = "http://localhost:8080/fornecedores";

    // Verifica se os dados já foram buscados
    if (suppliersFetched) {
      return Promise.resolve();
    }

    return axios.get(suppliersEndpoint).then((response) => {
      setSuppliers(response.data);
      setSuppliersFetched(true); // Atualiza a flag indicando que os dados foram buscados
    });
  };

  async function getOrders() {

    const getSupplierOrders = orders_endpoint + suppliersOrders;
    const getProductsOrders = orders_endpoint + productsOrders;


    try {

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

  const editSupplier = (order) => {
    setIsEditing(true);
    setSelectedInitialValues(order);
    setModalTitle("Editar Fornecedor - ID: " + order.id);
    const ModalWorkerButton = document.getElementById("ModalCRUD");
    ModalWorkerButton?.click();
  };

  const deleteOrder = (order) => {

    var deleteOrder;
    if(order.tipo == "ENTRADA") {
      deleteOrder = orders_endpoint + suppliersOrders + "/delete"
    } else {
      deleteOrder = orders_endpoint + productsOrders + "/delete"
    }

    try{
      axios.delete(deleteOrder, {
        data: {
          id: order.id,
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
      toast({
        title: "Erro! Não foi possível excluir o pedido!",
        status: "error",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
    }

  };

  const openInfoModal = (values) => {
    console.log(user)
    console.log("openInfoModal", values);

    const data = [];
    const titles = ["ID", "Status", "Tipo", "Data", "Usuário", "Fornecedor", "Produtos"];
    const keys = ["id", "status", "tipo", "data", "nome", "nome", "produtos"];
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
        } else if(index < 6) {
          if(values["fornecedor"] === null) {
            return;
          }
          data.push({
            title,
            content: values["fornecedor"][keys[index]]
          })
        } else {
          const productsMap = values["items"].map((item, index) => {
            if(index < values["items"].length - 1) {
              return item["produto"]["nome"] + " | "
            } else {
              return item["produto"]["nome"]
            }
          })
          data.push({
            title,
            content: [...productsMap]
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
    console.log(isEditing)
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
        axios.put(updateOrder, request).then(res => {
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
          title: "Erro! Não foi possível editar o cadastro do fornecedor!",
          status: "error",
          duration: 3000,
          isClosable: true,
          position: "top",
        });
      }

    } else {

      const insertOrder = "/insert";
      const supplierOrders = "Fornecedor";
      const productsOrders = "Estoque";

      if(values.fornecedor_id != null) {

        const parsedEndpoint = orders_endpoint + supplierOrders + insertOrder;

        var aux = [];
        values.produtos.map(produto => {
          aux.push({
            produto: {
              id: produto.id
            },
            quantidade: produto.quantidade
          })
        })

        var requestFornecedor = {
          itens: aux,
          fornecedor: {
            id: values.fornecedor_id
          },
          status: 'CRIADO',
          usuario: {
            id: user['id']
          }
        }
      
        try {
          axios.post(parsedEndpoint, requestFornecedor).then((res) => {
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
        const parsedEndpoint = orders_endpoint + productsOrders + insertOrder;

        var aux = [];
        values.produtos.map(produto => {
          aux.push({
            produto: {
              id: produto.id
            },
            quantidade: produto.quantidade
          })
        })

        var requestEstoque = {
          itens: aux,
          status: 'CRIADO',
          usuario: {
            id: user['id']
          }
        }

        try {
          axios.post(parsedEndpoint, requestEstoque).then((res) => {
            if(res.status === 405) {
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


    }
    const CloseModalSupplierIcon = document.getElementById(
      "CloseModalSupplierIcon"
    );
    CloseModalSupplierIcon?.click();
  };

  useEffect(() => {
    async function init() {
      await getProducts();
      await getSuppliers();
      await getOrders();
    }

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
          <OrderForm
            onSubmit={onSubmitOrderForm}
            is_editing={isEditing}
            initial_values={selectedInitialValues}
            products={products}
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
              const filtered = [];
              filtered.push( data.filter((pedido) => {
                return (
                  pedido.id ||
                  pedido.status.toLowerCase().includes(value.toLowerCase())
                );
              }));
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
        onClickDelete={deleteOrder}
      />
    </Container>
  );
};

export default OrdersPage;
