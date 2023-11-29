package com.system.backend.services;

import com.system.backend.entities.MailInfos;
import com.system.backend.entities.Relatorio;
import org.springframework.stereotype.Service;

/**
 * Classe de serviços relacionados ao envio de email
 * @author aronque
 */
@Service
public interface EmailService {


    /**
     * Envia um email com o relatório anexado e com base nos dados fornecidos
     * @author aronque
     * @param email Objeto relatorio com as informações para o email
     */
    public void sendEmail(Relatorio email);


    /**
     * Envia um email com base nos dados fornecidos
     * @author aronque
     * @param email Objeto que contém as informações para o email
     */
    public void sendEmail(MailInfos message);
}
