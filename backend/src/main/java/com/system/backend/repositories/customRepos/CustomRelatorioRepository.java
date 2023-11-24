package com.system.backend.repositories.customRepos;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomRelatorioRepository {

    public void execRelatorio(Class<?> relatorioClass);
}
