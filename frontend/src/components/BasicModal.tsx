import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
  Button,
} from "@chakra-ui/react";
import React from "react";

interface BasicModalProps {
  button_text: string;
  button_icon?: React.ReactNode;
  modal_title: string;
  button_color_scheme?: string;
  onSubmit?: () => void; // in the future it will probably return a promise
  children?: React.ReactNode;
  is_editing?: boolean;
  onCloseModalWorker?: () => void;
}

export const BasicModal: React.FC<BasicModalProps> = ({
  button_text,
  button_icon,
  modal_title,
  button_color_scheme = "blue",
  children,
  onCloseModalWorker = () => {},
}) => {
  const { isOpen, onOpen, onClose } = useDisclosure();

  return (
    <>
      <Button onClick={onOpen} colorScheme={button_color_scheme} id="ModalCRUD">
        {button_icon}
        {button_text}
      </Button>

      <Modal
        isOpen={isOpen}
        onClose={() => {
          onCloseModalWorker();
          onClose();
        }}
      >
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>{modal_title}</ModalHeader>
          <ModalCloseButton id="CloseModalWorkerIcon" />
          <ModalBody>{children}</ModalBody>
        </ModalContent>
      </Modal>
    </>
  );
};
