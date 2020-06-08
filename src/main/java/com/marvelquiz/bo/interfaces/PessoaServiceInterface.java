package com.marvelquiz.bo.interfaces;

import java.util.List;
import java.util.Optional;

import com.marvelquiz.bean.Pessoa;

public interface PessoaServiceInterface {
    List<Pessoa> findAll();
    Optional<Pessoa> findById(Long id);
    Pessoa save(Pessoa pessoa);
    Pessoa update(Pessoa pessoa);
    void delete(Pessoa pessoa);
}