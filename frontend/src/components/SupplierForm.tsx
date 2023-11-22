import React from "react";
import {
  FormControl,
  FormLabel,
  FormErrorMessage,
  Input,
  Button,
  Select
} from "@chakra-ui/react";
import { Field, Form, Formik, useFormikContext, useField } from "formik";
import {UFs} from "./ufs"

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
  function validateCnpj(value: any) {
    if (!value) {
      return "O CNPJ é obrigatório!";
    }
  }
  function validateEndereco(value: any) {
    if (!value) {
      return "O endereço é obrigatório!";
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
          <Field name="cnpj" validate={validateCnpj}>
            {({ field, form }) => (
              <FormControl
                isInvalid={form.errors.cnpj && form.touched.cnpj}
              >
                <FormLabel>CNPJ</FormLabel>
                <Input {...field} placeholder="CNPJ do fornecedor" />
                <FormErrorMessage>{form.errors.cnpj}</FormErrorMessage>
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
          <FormLabel>__________________________________________________________</FormLabel>
          <FormLabel>Endereço</FormLabel>
          <Field name="rua" validate={validateName}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.rua && form.touched.rua}>
                <FormLabel>Rua</FormLabel>
                <Input {...field} placeholder="Rua do endereço" />
                <FormErrorMessage>{form.errors.rua}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="numero" validate={validateName}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.numero && form.touched.numero}>
                <FormLabel>Número</FormLabel>
                <Input {...field} placeholder="Número do endereço" />
                <FormErrorMessage>{form.errors.numero}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="bairro" validate={validateName}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.bairro && form.touched.bairro}>
                <FormLabel>Bairro</FormLabel>
                <Input {...field} placeholder="Bairro do endereço" />
                <FormErrorMessage>{form.errors.bairro}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="cidade" validate={validateName}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.cidade && form.touched.cidade}>
                <FormLabel>Cidade</FormLabel>
                <Input {...field} placeholder="Cidade do endereço" />
                <FormErrorMessage>{form.errors.cidade}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="estado" validate={validateName}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.estado && form.touched.estado}>
                <FormLabel>Estado(UF)</FormLabel>
                <Select {...field} placeholder="Selecione o Estado">
                  {UFs.map((uf: any) => {
                    return (
                      <option>
                        {uf}
                      </option>
                    );
                  })}
                </Select>
                <FormErrorMessage>{form.errors.estado}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="cep" validate={validateName}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.cep && form.touched.cep}>
                <FormLabel>CEP</FormLabel>
                <Input {...field} placeholder="CEP do endereço" />
                <FormErrorMessage>{form.errors.cep}</FormErrorMessage>
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
