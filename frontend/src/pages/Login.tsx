import { useState } from "react";
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
} from "@chakra-ui/react";
import { Field, Form, Formik } from "formik";
import { EmailIcon, LockIcon } from "@chakra-ui/icons";

const LoginPage = () => {
  const [showPassword, setShowPassword] = useState(false);

  const handleShowClick = () => setShowPassword(!showPassword);

  function onLoginSubmit(values: any, actions: any) {
    setTimeout(() => {
      actions.setSubmitting(false);
      console.log(values);
      if (values.email === "abc@abc" && values.password === "123") {
        window.location.href = "/";
      }
    }, 1000);
  }

  function validateEmail(value: any) {
    if (!value) {
      return "Digite o e-mail.";
    }
  }

  function validatePassword(value: any) {
    if (!value) {
      return "Digite a senha.";
    }
  }

  return (
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
        <Avatar bg="#0098e5" />
        <Heading color="#0098e5">Bem-vindo</Heading>
        <Box minW={{ base: "90%", md: "468px" }}>
          <Formik
            onSubmit={onLoginSubmit}
            initialValues={{ email: "", password: "" }}
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
                          <Input {...field} type="email" placeholder="E-mail" />
                        </InputGroup>
                        <FormErrorMessage>{form.errors.email}</FormErrorMessage>
                      </FormControl>
                    )}
                  </Field>
                  <Field name="password" validate={validatePassword}>
                    {({ field, form }) => (
                      <FormControl
                        isInvalid={
                          form.errors.password && form.touched.password
                        }
                      >
                        <InputGroup>
                          <InputLeftElement
                            pointerEvents="none"
                            children={<LockIcon />}
                          />
                          <Input
                            {...field}
                            type={showPassword ? "text" : "password"}
                            placeholder="Senha"
                          />
                          <InputRightElement width="4.5rem">
                            <Button
                              h="1.75rem"
                              size="sm"
                              onClick={handleShowClick}
                            >
                              {showPassword ? "Esconder" : "Mostrar"}
                            </Button>
                          </InputRightElement>
                        </InputGroup>
                        <FormErrorMessage>
                          {form.errors.password}
                        </FormErrorMessage>
                      </FormControl>
                    )}
                  </Field>
                  <Button
                    borderRadius={0}
                    type="submit"
                    variant="solid"
                    colorScheme="blue"
                    width="full"
                    isLoading={props.isSubmitting}
                  >
                    ENTRAR
                  </Button>
                </Stack>
              </Form>
            )}
          </Formik>
        </Box>
      </Stack>
      <Box>
        É novo aqui?{" "}
        <Link color="#0098e5" href="/registrar">
          Registrar-se
        </Link>
      </Box>
    </Flex>
  );
};

export default LoginPage;
