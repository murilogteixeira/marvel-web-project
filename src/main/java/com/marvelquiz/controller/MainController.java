package com.marvelquiz.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import com.marvelquiz.backend.model.character.DataReturnWithCharacter;
import com.marvelquiz.backend.model.comics.DataReturnWithComic;
import com.marvelquiz.backend.model.events.DataReturnWithEvent;
import com.marvelquiz.bean.character2.Character;
import com.marvelquiz.bean.comics2.Items;
import com.marvelquiz.bean.comics2.Comic;
import com.marvelquiz.bean.events2.Event;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


//Main Controller 
@Controller
public class MainController {

    // @Autowired
    // private PessoaService service;

    private String scheme = "https";
    private String host = "marvel-web-project.herokuapp.com";

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("activeTab", "home");
        return "index";
    }

    // @RequestMapping("/db")
    // public String getDB(Map<String, Object> model) {

    //     List<Pessoa> lista = service.findAll();

    //     if (lista != null && !lista.isEmpty()) {
    //         List<String> output = new ArrayList<>();
    //         for (Pessoa p : lista) {
    //             output.add(p.toString());
    //         }
    //         model.put("records", output);
    //         return "db";
    //     } else
    //         model.put("message", "No items found");
    //     return "error";
    // }

    final String characterRequestMapping = "/characters";
    @RequestMapping(characterRequestMapping)
    public String chacarterPresentation(Map<String, Object> model) {
        System.out.println("Request: " + characterRequestMapping);
        model.put("activeTab", "characters");
        ArrayList<Character> resultados = getCharacters();
        model.put("records", resultados);
        return "character-presentation";
    }

    final String comicsRequestMapping = "/comics";
    @RequestMapping(comicsRequestMapping)
    public String comicsPresentation(Map<String, Object> model) {
        System.out.println("Request: " + comicsRequestMapping);
        model.put("activeTab", "comics");
        ArrayList<Comic> resultados = getComicsResults();
        ArrayList<String> creatorsArray = new ArrayList<String>();
        model.put("records", resultados);

        for (Comic resultado : resultados) {
            ArrayList<Items> items = resultado.getCreators().getItems();

            if (items.size() == 0) {
                creatorsArray.add("Unknown author");
            } else {
                for (Items item : items) {
                    creatorsArray.add(item.getName());
                }
            }
        }

        // System.out.println("Creadores: " + creatorsArray);

        // model.put("date", dataLancamento);
        return "comic-presentation";
    }

    final String eventsRequestMapping = "/events";
    @RequestMapping(eventsRequestMapping)
    public String eventPresentation(Map<String, Object> model) {
        System.out.println("Request: " + eventsRequestMapping);
        model.put("activeTab", "events");
        ArrayList<Event> resultados = getEventResults();
        model.put("records", resultados);
        return "event-presentation";
    }

    private ArrayList<Character> getCharacters() {
        int limite = 10;
        int total = 1493;

        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme(scheme).host(host)
        // .scheme("http").host("localhost").port(5000)
        .path("/api/characters")
        .queryParam("limit", limite)
        // .queryParam("offset", randomInt(limite, total))
        .build();

        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<DataReturnWithCharacter> entity;
            entity = template.getForEntity(uri.toUriString(), DataReturnWithCharacter.class);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            System.out.println(ts + " " + entity.getStatusCode());
            return entity.getBody().getResults();
        } catch (RestClientException e) {
            System.out.println(e);
            return null;
        }
    }

    private ArrayList<Comic> getComicsResults() {
        //limite de records por página
        int limite = 10;
        //total de records na api
        int total = 70000;
        
        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme(scheme).host(host)
        // .scheme("http").host("localhost").port(5000)
        .path("/api/comics")
        .queryParam("limit", limite)
        .build();
        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<DataReturnWithComic> entity;
            entity = template.getForEntity(uri.toUriString(), DataReturnWithComic.class);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            System.out.println(ts + " " + entity.getStatusCode());
            return entity.getBody().getResults();
        } catch (RestClientException e) {
            System.out.println(e);
            return null;
        }
    }

    private ArrayList<Event> getEventResults() {
        //limite de records por página
        int limite = 10;
        //total de records na api
        int total = 75;

        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme(scheme).host(host)
        // .scheme("http").host("localhost").port(5000)     
        .path("/api/events")
        .queryParam("limit", limite)
        .build();

        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<DataReturnWithEvent> entity;
            entity = template.getForEntity(uri.toUriString(), DataReturnWithEvent.class);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            System.out.println(ts + " " + entity.getStatusCode());
            return entity.getBody().getResults();
        } catch (RestClientException e) {
            System.out.println(e);
            return null;
        }
    }

    private String md5(String texto) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(texto.getBytes(), 0, texto.length());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (Exception e) {
            return "error";
        }
    }
    
    private int randomInt(int limite, int total){
        int intervalo = total / limite;
        Random ran = new Random();
        return ran.nextInt(intervalo);
    }

}