package com.system.backend.services;

import com.system.backend.entities.Relatorio;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public void sendEmail(Relatorio email);
}
