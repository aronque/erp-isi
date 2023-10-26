import React from "react";
import {
  FormControl,
  FormLabel,
  FormErrorMessage,
  Input,
  Button,
} from "@chakra-ui/react";
import { Field, Form, Formik } from "formik";

interface SupplierFormProps {
  onSubmit: (values?: any) => void;
  is_editing?: boolean;
  initial_values?: any;
}

export const SupplierForm: React.FC<SupplierFormProps> = ({
  onSubmit = () => {},
  is_editing = false,
  initial_values = {},
}) => {
  function validateName(value: any) {
    if (!value) {
      return "O nome é obrigatório!";
    }
  }
  function validateContact(value: any) {
    if (!value) {
      return "O contato é obrigatório!";
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
              <FormControl isInvalid={form.errors.nome && form.touched.nome}>
                <FormLabel>Nome</FormLabel>
                <Input {...field} placeholder="nome do fornecedor" />
                <FormErrorMessage>{form.errors.nome}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="contato" validate={validateContact}>
            {({ field, form }) => (
              <FormControl
                isInvalid={form.errors.contato && form.touched.contato}
              >
                <FormLabel>Contato</FormLabel>
                <Input {...field} placeholder="contato do fornecedor" />
                <FormErrorMessage>{form.errors.contato}</FormErrorMessage>
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
