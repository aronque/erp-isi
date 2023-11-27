import React from "react";
import {
  FormControl,
  FormLabel,
  FormErrorMessage,
  Input,
  Button,
  Select,
  Flex
} from "@chakra-ui/react";
import { Field, FieldArray, Form, Formik, useField } from "formik";

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


  return (
    <Formik
      initialValues={{
        ...initial_values,
      fornecedor_id: initial_values.fornecedor_id,
      produtos: initial_values.produtos || []}}
      onSubmit={onSubmit}
    >
      {(props) => (
        <Form>
          {/* Restante do código... */}

          <Field name="fornecedor_id">
            {({ field, form }) => (
              <FormControl
                isInvalid={
                  form.errors.fornecedor_id && form.touched.fornecedor_id
                }
              >
                <FormLabel>Fornecedor</FormLabel>
                <Select
                  {...field}
                  placeholder="Selecione o fornecedor"
                  onChange={(e) => {
                    const selectedSupplierId = e.target.value;
                    form.setFieldValue('fornecedor_id', selectedSupplierId);
                    form.setFieldValue('produtos', []); // Limpa a lista de produtos ao mudar de fornecedor
                  }}
                >
                  {suppliers.map((supplier: any) => (
                    <option key={supplier.id} value={supplier.id}>
                      {supplier.nome}
                    </option>
                  ))}
                </Select>
                <FormErrorMessage>{field.errors}</FormErrorMessage>
              </FormControl>
            )}
          </Field>

          <FieldArray name="produtos">
  {({ push, remove, form }) => (
    <FormControl>
      <FormLabel>Produtos</FormLabel>
      <Flex mb={2}>
        <Select
          name={`produtos[0].id`}
          placeholder="Selecione o produto"
          onChange={(e) => {
            const selectedProductId = e.target.value;
            form.setFieldValue(`produtos[0].id`, selectedProductId);
          }}
        >
          {products.map((product: any) => {
                    if(form.values.fornecedor_id != null) {
                      if(product.fornecedor.id == form.values.fornecedor_id) {
                        return (
                          <option key={product.id} value={product.id}>
                            {product.nome}
                          </option>
                        );
                      }
                    } else {
                      return (
                        <option key={product.id} value={product.id}>
                          {product.nome}
                        </option>
                      );
                    }
                  })}
        </Select>
        <Field
          name={`produtos[0].quantidade`}
          validate={(value) => (!value ? "Campo obrigatório" : undefined)}
        >
          {({ field, form }) => (
            <Input
              {...field}
              placeholder="Quantidade"
              ml={2}
              type="number"
            />
          )}
        </Field>
        <Button
          type="button"
          colorScheme="red"
          ml={2}
          onClick={() => remove(0)}
        >
          Remover
        </Button>
      </Flex>
      {products && products.length > 0 && (
        <>
          {form.values.produtos.slice(1).map((produto, index) => (
            <Flex key={index} mb={2}>
              <Select
                name={`produtos[${index + 1}].id`}
                placeholder="Selecione o produto"
                onChange={(e) => {
                  const selectedProductId = e.target.value;
                  form.setFieldValue(
                    `produtos[${index + 1}].id`,
                    selectedProductId
                  );
                }}
              >
                {products
                  .map((product: any) => {
                    if(form.values.fornecedor_id != null) {
                      if(product.fornecedor.id == form.values.fornecedor_id) {
                        return (
                          <option key={product.id} value={product.id}>
                            {product.nome}
                          </option>
                        );
                      }
                    } else {
                      return (
                        <option key={product.id} value={product.id}>
                          {product.nome}
                        </option>
                      );
                    }
                  })}
              </Select>
              <Field
                name={`produtos[${index + 1}].quantidade`}
                validate={(value) =>
                  !value ? "Campo obrigatório" : undefined
                }
              >
                {({ field, form }) => (
                  <Input
                    {...field}
                    placeholder="Quantidade"
                    ml={2}
                    type="number"
                  />
                )}
              </Field>
              <Button
                type="button"
                colorScheme="red"
                ml={2}
                onClick={() => remove(index + 1)}
              >
                Remover
              </Button>
            </Flex>
          ))}
          <Button
            type="button"
            colorScheme="green"
            onClick={() => push({ id: "", quantidade: 1 })}
          >
            Adicionar Produto
          </Button>
        </>
      )}
    </FormControl>
  )}
</FieldArray>

          <Button type="submit">Enviar</Button>

          {/* Restante do código... */}
        </Form>
      )}
    </Formik>
  );
};
