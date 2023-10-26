import React from "react";
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

const HomePage: React.FC = () => {
  const [beginDate, setBeginDate] = React.useState<Date | null>(null);
  const [endDate, setEndDate] = React.useState<Date | null>(null);

  const [produtoFornecedor, setProdutoFornecedor] = React.useState<any[]>([
    { produto: "Óculos", fornecedores: 3 },
    { produto: "Camiseta", fornecedores: 2 },
    { produto: "Calça", fornecedores: 5 },
  ]);
  const [emissoesPedidos, setEmissoesPedidos] = React.useState<any[]>([
    { data_emissao: "2021-01-01", quantidade: 2 },
    { data_emissao: "2021-01-02", quantidade: 3 },
    { data_emissao: "2021-01-03", quantidade: 5 },
    { data_emissao: "2021-01-04", quantidade: 4 },
    { data_emissao: "2021-01-05", quantidade: 7 },
  ]);
  const [fornecedorProduto, setFornecedorProduto] = React.useState<any[]>([
    { fornecedor: "Fornecedor 1", produtos: 3 },
    { fornecedor: "Fornecedor 2", produtos: 2 },
    { fornecedor: "Fornecedor 3", produtos: 1 },
  ]);

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
              Produto - Fornecedor
            </Text>
            <VictoryChart domainPadding={20}>
              <VictoryBar
                style={{ data: { fill: "#c43a31" } }}
                data={produtoFornecedor}
                x="produto"
                y="fornecedores"
              />
            </VictoryChart>
          </CardBody>
        </Card>
        <Card>
          <CardBody>
            <Text fontSize={{ base: "2xl", md: "3xl" }}>
              Emissões de pedidos
            </Text>
            <VictoryChart>
              <VictoryLine
                style={{
                  data: { stroke: "#c43a31" },
                  parent: { border: "1px solid #ccc" },
                }}
                data={emissoesPedidos}
                x="data_emissao"
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
                data={fornecedorProduto}
                x="fornecedor"
                y="produtos"
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
