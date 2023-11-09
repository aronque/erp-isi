package com.system.backend.repositories.customRepos;

import com.system.backend.entities.Fornecedor;

import java.util.List;

public interface CustomFornecedorRepository {

    public List<Fornecedor> findBy(Fornecedor fornecedor);
}
