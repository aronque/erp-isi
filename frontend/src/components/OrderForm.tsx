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

interface OrderFormProps {
  onSubmit: (values?: any) => void;
  is_editing?: boolean;
  initial_values?: any;
  suppliers?: any;
  products?: any;
}

export const OrderForm: React.FC<OrderFormProps> = ({
  onSubmit = () => {},
  is_editing = false,
  initial_values = {},
  suppliers = [],
  products = [],
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
          <Field name="status" >
            {({ field, form }) => (
              <FormControl
                isInvalid={
                  form.errors.fornecedor_id && form.touched.fornecedor_id
                }
              >
                <FormLabel>Status</FormLabel>
                <Select {...field} placeholder="Selecione o Status">
                  {[{id: 1, value: "CRIADO"}, {id: 2, value: "PROCESSANDO"}, {id: 3, value: "PROCESSADO"}, {id: 4, value: "FINALIZADO"}].map((status: any) => {
                    return (
                      <option key={status.id} value={status.id}>
                        {status.value}
                      </option>
                    );
                  })}
                </Select>
                <FormErrorMessage>{form.errors.fornecedor_id}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="fornecedor_id">
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
          <Field name="produtos">
            {({ field, form }) => (
              <FormControl
                isInvalid={
                  form.errors.fornecedor_id && form.touched.fornecedor_id
                }
              >
                <FormLabel>Fornecedor</FormLabel>
                <Select {...field} placeholder="Selecione o produto">
                  {
                  useField("fornecedor_id").filter((field) => {
                    console.log(initial_values.fornecedor_id);

                    if(field === null) {
                      products.map((product: any) => {
                        return (
                          <option key={product.id} value={product.id}>
                            {product.nome}
                          </option>
                        )
                      });
                    } else {
                      products.map((product: any) => {
                        if(product.fornecedor["id"] === field) {
                          return (
                            <option key={product.id} value={product.id}>
                              {product.nome}
                            </option>
                          )
                        }
                      });                    
                    }
                  })
                }
                </Select>

                <FormErrorMessage>{form.errors.fornecedor_id}</FormErrorMessage>
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
