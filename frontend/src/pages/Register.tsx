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
  FormErrorMessage,
} from "@chakra-ui/react";
import { Field, Form, Formik } from "formik";
import { EmailIcon, LockIcon } from "@chakra-ui/icons";

const RegisterPage = () => {
  function onRegisterSubmit(values: any, actions: any) {
    setTimeout(() => {
      actions.setSubmitting(false);
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

  function validatePasswordConfirmation({ values }) {
    if (!values.password_confirmation) {
      return "Digite a confirmação da senha.";
    } else if (values.password_confirmation !== values.password) {
      return "As senhas não coincidem.";
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
        <Heading color="#0098e5">Registre-se</Heading>
        <Box minW={{ base: "90%", md: "468px" }}>
          <Formik
            onSubmit={onRegisterSubmit}
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
                            type="password"
                            placeholder="Senha"
                          />
                        </InputGroup>
                        <FormErrorMessage>
                          {form.errors.password}
                        </FormErrorMessage>
                      </FormControl>
                    )}
                  </Field>
                  <Field
                    name="password_confirmation"
                    validate={() => validatePasswordConfirmation(props)}
                  >
                    {({ field, form }) => (
                      <FormControl
                        isInvalid={
                          form.errors.password_confirmation &&
                          form.touched.password_confirmation
                        }
                      >
                        <InputGroup>
                          <InputLeftElement
                            pointerEvents="none"
                            children={<LockIcon />}
                          />
                          <Input
                            {...field}
                            type="password"
                            placeholder="Confirme sua senha"
                          />
                        </InputGroup>
                        <FormErrorMessage>
                          {form.errors.password_confirmation}
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
                    REGISTRAR
                  </Button>
                </Stack>
              </Form>
            )}
          </Formik>
        </Box>
      </Stack>
      <Box>
        Já tem conta?{" "}
        <Link color="#0098e5" href="/entrar">
          Entre
        </Link>
      </Box>
    </Flex>
  );
};

export default RegisterPage;
