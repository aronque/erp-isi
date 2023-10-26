import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalCloseButton,
  Box,
  Text,
  Grid,
  Spacer,
} from "@chakra-ui/react";
import React from "react";

export interface InfoModalProps {
  modal_title: string;
  data: Data[];
  is_open: boolean;
  closeInfoModal: () => void;
}
interface Data {
  title: string;
  content: string;
}

export const InfoModal: React.FC<InfoModalProps> = ({
  modal_title,
  data,
  is_open,
  closeInfoModal,
}) => {
  return (
    <Modal isOpen={is_open} onClose={closeInfoModal} size="2xl">
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>{modal_title}</ModalHeader>
        <ModalCloseButton id="CloseModalWorkerIcon" />
        <ModalBody>
          <Grid templateColumns="repeat(2, 1fr)" gap={6}>
            {data.map((item) => (
              <Box>
                <Text fontWeight="bold" fontSize="lg">
                  {item.title}
                </Text>
                <Text>{item.content}</Text>
              </Box>
            ))}
          </Grid>
          <Spacer h={5} />
        </ModalBody>
      </ModalContent>
    </Modal>
  );
};
