package com.system.backend.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RelatorioService {

    public String getRelatorioType();

    public List<String> getRelatorioCsv();
}
