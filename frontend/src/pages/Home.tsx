import React, { useContext, useEffect, useState, Component } from "react";
import axios from "axios";
import { BasicTable, BasicModal, InfoModal, ProductForm } from "../components";
import {
  Text,
  Container,
  Flex,
  Spacer,
  Input,
  InputGroup,
  InputLeftElement,
  Card,
  Grid,
  CardBody,
} from "@chakra-ui/react";
import { VictoryBar, VictoryChart, VictoryLine } from "victory";



var prodPedBodyData = [];
var movMesData = [];
var statusPedData = [];


const HomePage: React.FC = () => {
  const [beginDate, setBeginDate] = React.useState<Date | null>(null);
  const [endDate, setEndDate] = React.useState<Date | null>(null);

  const graficosEndpoint = "https://erp-isi-backend-3bfe7b8310b8.herokuapp.com/graficos"
  const graficoProdPedido = graficosEndpoint + "/prodPedido";
  const graficoMovMes = graficosEndpoint + "/movMes";
  const graficoStatusPedidos = graficosEndpoint + "/statusPedidos";

  var request  = {
    filtroInicial: beginDate,
    filtroFinal: endDate
  }

  useEffect(() => {
    // getProdPedData();
    // getStatusPedData();
    // getMovMesData();

    setProdutosPedido(prodPedBodyData);
    setMovMes(movMesData);
    setStatusPed(statusPedData);
  }, []);

  // const getProdPedData = async () => {
  //   await axios.post(graficoProdPedido, request).then(response => {
  //     var index = 0;
  //     for(const [key, value] of Object.entries(response.data)) {

  //       prodPedBodyData[index++] = {prod: key, quantidade: value};
  //     }
  //   })
  // }

  // const getMovMesData = async () => {
  //   await axios.post(graficoMovMes, request).then(response => {
  //     var index = 0;
  //     for(const [key, value] of Object.entries(response.data)) {

  //       prodPedBodyData[index++] = {mes: key, quantidade: value};
  //     }
  //   })
  // }

  // const getStatusPedData = async () => {
  //   await axios.post(graficoStatusPedidos, request).then(response => {
  //     var index = 0;
  //     for(const [key, value] of Object.entries(response.data)) {

  //       prodPedBodyData[index++] = {status: key, quantidade: value};
  //     }
  //   })
  // }

  //request gráfico relação quantidade de Pedidos por Produto
  const [produtosPedido, setProdutosPedido] = React.useState<any[]>([...prodPedBodyData]);

  //request gráfico relação quantidade de Movimentações por Mês
  const [movMes, setMovMes] = React.useState<any[]>([...movMesData]);

  //request gráfico relação quantidade de Pedidos por Status
  // var responseStatusPedido = axios.post(graficoStatusPedidos, request).then(response => {
  //   return response;
  // });
  const [statusPed, setStatusPed] = React.useState<any[]>([...statusPedData]);

  return (
    <Container maxWidth={1000}>
      <Spacer height={4} />
      <Flex
        justifyContent={{
          base: "center",
          md: "space-between",
        }}
        alignItems="center"
        flexWrap="wrap"
        gap={3}
      >
        <Text fontSize={{ base: "2xl", md: "3xl" }}>Dashboard</Text>
        <Flex alignItems="center">
          <Flex
            alignItems="center"
            gap={3}
            flexDir={{ base: "column", md: "row" }}
          >
            <InputGroup maxWidth={200}>
              <InputLeftElement pointerEvents="none" />
              <Input
                type="date"
                onChange={(e) => setBeginDate(new Date(e.target.value))}
              />
            </InputGroup>
            <Text>até</Text>
            <InputGroup maxWidth={200}>
              <InputLeftElement pointerEvents="none" />
              <Input
                type="date"
                onChange={(e) => setEndDate(new Date(e.target.value))}
              />
            </InputGroup>
          </Flex>
        </Flex>
      </Flex>
      <Spacer height={25} />

      <Grid
        templateColumns={{
          base: "repeat(1, 1fr)",
          md: "repeat(2, 1fr)",
        }}
        gap={6}
      >
        <Card>
          <CardBody>
            <Text fontSize={{ base: "2xl", md: "3xl" }}>
              Produto - Pedidos
            </Text>
            <VictoryChart domainPadding={20}>
              <VictoryBar
                style={{ data: { fill: "#c43a31" } }}
                data={produtosPedido}
                x="prod"
                y="quantidade"
              />
            </VictoryChart>
          </CardBody>
        </Card>
        <Card>
          <CardBody>
            <Text fontSize={{ base: "2xl", md: "3xl" }}>
              Movimentações por Mês
            </Text>
            <VictoryChart>
              <VictoryLine
                style={{
                  data: { stroke: "#c43a31" },
                  parent: { border: "1px solid #ccc" },
                }}
                data={movMes}
                x="mes"
                y="quantidade"
              />
            </VictoryChart>
          </CardBody>
        </Card>
        <Card>
          <CardBody>
            <Text fontSize={{ base: "2xl", md: "3xl" }}>
              Fornecedor - Produto
            </Text>
            <VictoryChart domainPadding={20}>
              <VictoryBar
                style={{ data: { fill: "#31aec4" } }}
                data={statusPed}
                x="status"
                y="quantidade"
              />
            </VictoryChart>
          </CardBody>
        </Card>
      </Grid>
      <Spacer height={25} />
    </Container>
  );
};

export default HomePage;
