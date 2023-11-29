import { useState, useContext } from "react";
import axios from "axios";
import {
  Flex,
  Heading,
  Input,
  Button,
  InputGroup,
  Stack,
  InputLeftElement,
  Box,
  Link,
  Avatar,
  FormControl,
  InputRightElement,
  FormErrorMessage,
  useToast,
  FormLabel,
  Select,
  Container
} from "@chakra-ui/react";
import { Field, Form, Formik } from "formik";
import { ThemeContext } from "../providers/ThemeProvider";
import { EmailIcon, LockIcon } from "@chakra-ui/icons";
import { Reports } from "../components/utils/reports";
import getSession from "../components/getSession";


const ReportPage = () => {
  const user = JSON.parse(getSession());
  console.log(user)
  const { currentTheme } = useContext(ThemeContext);

  const toast = useToast();

  async function onBuildSubmit(values: any, actions: any) {
    
    const reportsEndpoint = "http://localhost:8080/relatorios"; 

    const aux = Reports.find((report) => report.id == values.relatorio)
    var requestEndpoint = reportsEndpoint + aux.endPoint;

    var request = {
      email: values.email,
      requestUser: {
        id: user['id']
      }
    }

    try {
      await axios.post(requestEndpoint, request).then(res => {
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
            title: "Erro! Não foi possível gerar o relatório!",
            status: "error",
            duration: 3000,
            isClosable: true,
            position: "top",
          });
        } else {
          toast({
            title: "Operação realizada com sucesso. O relatório será enviado para o email fornecido.",
            status: "success",
            duration: 3000,
            isClosable: true,
            position: "top",
          });
        }
      });
      
    } catch(err) {
      toast ({
        title: "Erro ao gerar relatório",
        status: "error",
        duration: 3000,
        isClosable: true,
        position: "top",
      });
    }
  }

  function validateEmail(value: any) {
    if (!value) {
      return "Digite o e-mail.";
    }
  }

  function validateRelatorio(value: any) {
    if (!value) {
      return "Relatorio é obrigatório..";
    }
  }

  return (
    <Container maxWidth={1000}>
      <Flex
      flexDirection="column"
      width="100wh"
      height="100vh"
      backgroundColor="#faf3ef"
      justifyContent="center"
      alignItems="center"
    >
      <Stack
        flexDir="column"
        mb="2"
        justifyContent="center"
        alignItems="center"
      >
        <Box minW={{ base: "90%", md: "468px" }}>
          <Formik
            onSubmit={onBuildSubmit}
            initialValues={{ email: ""}}
          >
            {(props) => (
              <Form>
                <Stack
                  spacing={4}
                  p="1rem"
                  backgroundColor="whiteAlpha.900"
                  boxShadow="md"
                >
                  <Field name="email" validate={validateEmail}>
                    {({ field, form }) => (
                      <FormControl
                        isInvalid={form.errors.email && form.touched.email}
                      >
                        <InputGroup>
                          <InputLeftElement
                            pointerEvents="none"
                            children={<EmailIcon />}
                          />
                          <Input colorScheme={currentTheme === "light" ? "blackAlpha" : "facebook"} {...field} type="email" placeholder="E-mail que receberá o relatório" />
                        </InputGroup>
                        <FormErrorMessage>{form.errors.email}</FormErrorMessage>
                      </FormControl>
                    )}
                  </Field>
                  <Field name="relatorio" validate={validateRelatorio}>
                      {({ field, form }) => (
                      <FormControl
                        isInvalid={
                          form.errors.fornecedor_id && form.touched.fornecedor_id
                        }
                      >
                        <FormLabel>Relatórios</FormLabel>
                        <Select {...field} placeholder="Selecione o relatório">
                          {Reports.map((report: any) => {
                            return (
                              <option key={report.id} value={report.id}>
                                {report.desc}
                              </option>
                            );
                          })}
                        </Select>

                        <FormErrorMessage>{form.errors.fornecedor_id}</FormErrorMessage>
                      </FormControl>
                    )}
                  </Field>
                  <Button
                    borderRadius={0}
                    type="submit"
                    variant="solid"
                    colorScheme={currentTheme === "light" ? "blue" : "facebook"}
                    width="full"
                    isLoading={props.isSubmitting}
                  >
                    GERAR
                  </Button>
                </Stack>
              </Form>
            )}
          </Formik>
        </Box>
      </Stack>
    </Flex>
    </Container>
  );
};

export default ReportPage;
