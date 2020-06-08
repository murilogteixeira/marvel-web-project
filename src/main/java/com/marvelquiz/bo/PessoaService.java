package com.marvelquiz.bo;

import java.util.List;
import java.util.Optional;

import com.marvelquiz.bean.Pessoa;
import com.marvelquiz.dao.PessoaRepository;
import com.marvelquiz.bo.interfaces.PessoaServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService implements PessoaServiceInterface {
	@Autowired
	private PessoaRepository repository;

	@Override
	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Pessoa> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Pessoa save(Pessoa pessoa) {
		return repository.save(pessoa);
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		return repository.save(pessoa);
	}

	@Override
	public void delete(Pessoa pessoa) {
		repository.delete(pessoa);
	}

}
