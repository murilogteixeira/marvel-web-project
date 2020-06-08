package com.marvelquiz.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.marvelquiz.bean.character.Personagem;
import com.marvelquiz.bean.Pessoa;
import com.marvelquiz.bo.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PessoaController {

    @Autowired
    private PessoaService service;

    @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
    public List<Pessoa> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> findById(@PathVariable(value = "id") long id) {
        Optional<Pessoa> pessoa = service.findById(id);
        if (pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa", method = RequestMethod.POST)
    public Pessoa save(@Valid @RequestBody Pessoa pessoa) {
        return service.save(pessoa);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pessoa> update(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPessoa) {
        Optional<Pessoa> oldPessoa = service.findById(id);
        if (oldPessoa.isPresent()) {
            Pessoa pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            service.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") long id) {
        Optional<Pessoa> pessoa = service.findById(id);
        if (pessoa.isPresent()) {
            service.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public void teste() {
        RestTemplate template = new RestTemplate();

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String privateKey = "ded1f16e4c678b8817e21f3b79fda2ea2153900c";
        String publicKey = "ab7fe0ebc4b57fc4cfd8c5cc155ec01c";
        String hash = "" + ts + privateKey + publicKey;
        String hashMD5 = md5(hash);

        UriComponents uri = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("gateway.marvel.com").port(443)        
            .path("v1/public/characters")
            .queryParam("ts", ts)
            .queryParam("apikey", publicKey)
            .queryParam("hash", hashMD5).build();

        String uriString = uri.toUriString();

        ResponseEntity<Personagem> entity = template.getForEntity(uriString, Personagem.class);
        System.out.println(entity.getBody().getData().getResults().get(0).getName());
        System.out.println(entity.getStatusCode());
        // return personagem;
    }

    private String md5(String texto) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(texto.getBytes(), 0, texto.length());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (Exception e) {
            return "Error";
        }
    }

}
