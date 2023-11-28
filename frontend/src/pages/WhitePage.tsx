import { useState } from "react";
import axios from "axios";
import {
  Container,
  Flex,
  Heading,
  Stack,
  Box,
  Avatar,
  useToast
} from "@chakra-ui/react";
import { Field, Form, Formik } from "formik";
import { EmailIcon, LockIcon } from "@chakra-ui/icons";
import getSession from "../components/getSession";

const WhitePage = () => {

  const user = getSession();
  console.log(user)

  const [showPassword, setShowPassword] = useState(false);
  const toast = useToast();

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
        </Box>
      </Stack>
    </Flex>
    </Container>
  );
};

export default WhitePage;
