import React from "react";
import {
  FormControl,
  FormLabel,
  FormErrorMessage,
  Input,
  Button,
  Select
} from "@chakra-ui/react";
import { Field, Form, Formik } from "formik";
import {Types} from './utils/userTypes'

interface WorkerFormProps {
  onSubmit: (values?: any) => void;
  is_editing?: boolean;
  initial_values?: any;
}

export const WorkerForm: React.FC<WorkerFormProps> = ({
  onSubmit = () => {},
  is_editing = false,
  initial_values = {},
}) => {
  function validateName(value: any) {
    if (!value) {
      return "O nome é obrigatório!";
    }
  }
  function validateEmail(value: any) {
    if (!value) {
      return "O email é obrigatório!";
    }
  }

  return (
    <Formik
      initialValues={initial_values}
      onSubmit={(values, actions) => {
        setTimeout(() => {
          actions.setSubmitting(false);
          onSubmit(values);
        }, 1000);
      }}
    >
      {(props) => (
        <Form>
          <Field name="nome" validate={validateName}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.name && form.touched.name}>
                <FormLabel>Nome</FormLabel>
                <Input {...field} placeholder="nome do usuário" />
                <FormErrorMessage>{form.errors.name}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="email" validate={validateEmail}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.email && form.touched.email}>
                <FormLabel>Email</FormLabel>
                <Input {...field} placeholder="email do usuário" />
                <FormErrorMessage>{form.errors.email}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="senha" validate={validateEmail}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.email && form.touched.email}>
                <FormLabel>Senha</FormLabel>
                <Input {...field} placeholder="Senha do usuário" />
                <FormErrorMessage>{form.errors.email}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="tipo" validate={validateName}>
            {({ field, form }) => (
              <FormControl>
                <FormLabel>Tipo</FormLabel>
                <Select {...field} placeholder="Selecione o tipo do usuário">
                  {Types.map((type: any) => {
                    return (
                      <option key={type.id} value={type.id}>
                        {type.desc}
                      </option>
                    );
                  })}
                </Select>
                <FormErrorMessage>{form.errors.estado}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Button
            mt={4}
            colorScheme="whatsapp"
            isLoading={props.isSubmitting}
            type="submit"
          >
            {is_editing ? "Editar" : "Cadastrar"}
          </Button>
        </Form>
      )}
    </Formik>
  );
};
