import React from "react";
import {
  FormControl,
  FormLabel,
  FormErrorMessage,
  Input,
  Button,
} from "@chakra-ui/react";
import { Field, Form, Formik } from "formik";

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
          <Field name="name" validate={validateName}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.name && form.touched.name}>
                <FormLabel>Nome</FormLabel>
                <Input {...field} placeholder="nome do funcionário" />
                <FormErrorMessage>{form.errors.name}</FormErrorMessage>
              </FormControl>
            )}
          </Field>
          <Field name="email" validate={validateEmail}>
            {({ field, form }) => (
              <FormControl isInvalid={form.errors.email && form.touched.email}>
                <FormLabel>Email</FormLabel>
                <Input {...field} placeholder="email do funcionário" />
                <FormErrorMessage>{form.errors.email}</FormErrorMessage>
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
