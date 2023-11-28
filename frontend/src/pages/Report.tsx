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


const ReportPage = () => {
  const { currentTheme } = useContext(ThemeContext);

  const toast = useToast();

  async function onBuildSubmit(values: any, actions: any) {
    
    const reportsEndpoint = "https://erp-isi-backend-3bfe7b8310b8.herokuapp.com/relatorios"; 

    const aux = Reports.find((report) => report.id == values.relatorio)
    var requestEndpoint = reportsEndpoint + aux.endPoint;

    var request = {
      email: values.email
    }

    try {
      await axios.post(requestEndpoint, request).then((res) => {
        if(res.status === 200) {
          toast ({
            title: "Sucesso! O relatório será enviado para o email inserido.",
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
