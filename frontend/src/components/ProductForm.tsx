import React from "react";
import {
  FormControl,
  FormLabel,
  FormErrorMessage,
  Input,
  Button,
  Select,
} from "@chakra-ui/react";
import { Field, Form, Formik } from "formik";

interface ProductFormProps {
  onSubmit: (values?: any) => void;
  is_editing?: boolean;
  initial_values?: any;
  suppliers?: any;
}

export const ProductForm: React.FC<ProductFormProps> = ({
  onSubmit = () => {},
  is_editing = false,
  initial_values = {},
  suppliers = [],
}) => {
  function validateName(value: any) {
    if (!value) {
      return "O nome do produto é obrigatório!";
    }
  }
  function validateSupplier(value: any) {
    if (!value) {
      return "O fornecedor é obrigatório!";
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
                <Input {...field} placeholder="nome do produto" />
                <FormErrorMessage>{form.errors.name}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="fornecedor_id" validate={validateSupplier}>
            {({ field, form }) => (
              <FormControl
                isInvalid={
                  form.errors.fornecedor_id && form.touched.fornecedor_id
                }
              >
                <FormLabel>Fornecedor</FormLabel>
                <Select {...field} placeholder="Selecione o fornecedor">
                  {suppliers.map((supplier: any) => {
                    return (
                      <option key={supplier.id} value={supplier.id}>
                        {supplier.nome}
                      </option>
                    );
                  })}
                </Select>

                <FormErrorMessage>{form.errors.fornecedor_id}</FormErrorMessage>
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
