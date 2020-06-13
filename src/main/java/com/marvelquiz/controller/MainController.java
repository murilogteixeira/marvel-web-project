package com.marvelquiz.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.marvelquiz.bean.character.Personagem;
import com.marvelquiz.bean.Pessoa;
import com.marvelquiz.bean.character.ResultsCharacter;
import com.marvelquiz.bean.comics.Comics;
import com.marvelquiz.bean.comics.Items;
import com.marvelquiz.bean.comics.ResultsComics;
import com.marvelquiz.bean.events.Events;
import com.marvelquiz.bean.events.ResultsEvent;
import com.marvelquiz.bo.PessoaService;

import com.marvelquiz.bean.quiz.PerguntasQuiz;
import com.marvelquiz.bean.quiz.Quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


//Main Controller 
@Controller
public class MainController {

    @Autowired
    private PessoaService service;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("activeTab", "home");
        return "index";
    }

    @RequestMapping("/db")
    public String getDB(Map<String, Object> model) {

        List<Pessoa> lista = service.findAll();

        if (lista != null && !lista.isEmpty()) {
            List<String> output = new ArrayList<>();
            for (Pessoa p : lista) {
                output.add(p.toString());
            }
            model.put("records", output);
            return "db";
        } else
            model.put("message", "No items found");
        return "error";
    }

    @RequestMapping("/characters")
    public String chacarterPresentation(Map<String, Object> model) {
        model.put("activeTab", "characters");
        ArrayList<ResultsCharacter> resultados = getCharacterResults(5, 1000);
        model.put("records", resultados);
        return "character-presentation";
    }

    @RequestMapping("/comics")
    public String comicsPresentation(Map<String, Object> model) {
        model.put("activeTab", "comics");
        ArrayList<ResultsComics> resultados = getComicsResults(5, 70000);
        ArrayList<String> creatorsArray = new ArrayList<String>();
        model.put("records", resultados);

        for (ResultsComics resultado : resultados) {
            ArrayList<Items> items = resultado.getCreators().getItems();

            if (items.size() == 0) {
                creatorsArray.add("Unknown author");
            } else {
                for (Items item : items) {
                    creatorsArray.add(item.getName());
                }
            }
        }

        System.out.println("Creadores: " + creatorsArray);

        // model.put("date", dataLancamento);
        return "comic-presentation";
    }

    @RequestMapping("/events")
    public String eventPresentation(Map<String, Object> model) {
        model.put("activeTab", "events");
        ArrayList<ResultsEvent> resultados = getEventResults(5, 75);
        model.put("records", resultados);
        return "event-presentation";
    }

    @RequestMapping("/quiz")
    public String quizPresentation(Map<String, Object> model){
        model.put("activeTab", "quiz");

        Quiz quiz = getQuizTitlePeriodoEvento();

        model.put("quiz", quiz);
        return "quiz-game";
    }

    private Quiz getQuizTitlePeriodoEvento(){
        PerguntasQuiz perguntas = new PerguntasQuiz();

        String pergunta = perguntas.getTitlePeriodoEvento();

        ArrayList<ResultsEvent> events = getEventResults(4, 90);
        String respostaCerta = "" + events.get(0).getStart() + " - " + events.get(0).getEnd(); 

        String conteudo = events.get(0).getTitle();
        boolean conteudoIsImage = false;

        // ArrayList<ResultsEvent> eventosErrados = new ArrayList<ResultsEvent>();

        // for(int i = 0; i < 3; i++){
            // eventosErrados = getEventResults(3, 370);
        // }

        String respostasErradas[] = new String[] {
            "" + events.get(1).getStart() + " - " + events.get(1).getEnd(),
            "" + events.get(2).getStart() + " - " + events.get(2).getEnd(),
            "" + events.get(3).getStart() + " - " + events.get(3).getEnd()
        };

        Quiz quiz = new Quiz(pergunta, respostaCerta, respostasErradas, conteudo, conteudoIsImage);

        return quiz;
    }

    private ArrayList<ResultsCharacter> getCharacterResults(int limite, int total) {
        // //limite de records por página
        // int limite = 5;
        // //total de records na api
        // int total = 1000;

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
        // .queryParam("nameStartsWith", "Cap")
        .queryParam("limit", limite)
        .queryParam("offset", randomInt(limite, total))
        .queryParam("ts", ts)
        .queryParam("apikey", publicKey)
        .queryParam("hash", hashMD5).build();

        String uriString = uri.toUriString();

        ResponseEntity<Personagem> entity = template.getForEntity(uriString, Personagem.class);
        System.out.println(entity.getBody().getData().getResults().get(0).getThumbnail().getPath());
        System.out.println(entity.getStatusCode());


        ArrayList<ResultsCharacter> resultados = entity.getBody().getData().getResults();

        return resultados;
    }

    private ArrayList<ResultsComics> getComicsResults(int limite, int total) {
        // //limite de records por página
        // int limite = 5;
        // //total de records na api
        // int total = 70000;
        
        RestTemplate template = new RestTemplate();

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String privateKey = "ded1f16e4c678b8817e21f3b79fda2ea2153900c";
        String publicKey = "ab7fe0ebc4b57fc4cfd8c5cc155ec01c";
        String hash = "" + ts + privateKey + publicKey;
        String hashMD5 = md5(hash);

        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme("https")
        .host("gateway.marvel.com").port(443)        
        .path("v1/public/comics")
        .queryParam("format", "comic")
        .queryParam("noVariants", "true")
        .queryParam("limit", limite)
        .queryParam("offset", randomInt(limite, total))
        .queryParam("ts", ts)
        .queryParam("apikey", publicKey)
        .queryParam("hash", hashMD5).build();

        String uriString = uri.toUriString();

        ResponseEntity<Comics> entity = template.getForEntity(uriString, Comics.class);
        System.out.println(entity.getBody().getData().getResults().get(0).getThumbnail().getPath());
        System.out.println(entity.getStatusCode());


        ArrayList<ResultsComics> resultados = entity.getBody().getData().getResults();

        return resultados;
    }

    private ArrayList<ResultsEvent> getEventResults(int limite, int total) {

        RestTemplate template = new RestTemplate();

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String privateKey = "ded1f16e4c678b8817e21f3b79fda2ea2153900c";
        String publicKey = "ab7fe0ebc4b57fc4cfd8c5cc155ec01c";
        String hash = "" + ts + privateKey + publicKey;
        String hashMD5 = md5(hash);

        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme("https")
        .host("gateway.marvel.com").port(443)        
        .path("v1/public/events")
        .queryParam("limit", limite)
        .queryParam("offset", randomInt(limite, total))
        .queryParam("ts", ts)
        .queryParam("apikey", publicKey)
        .queryParam("hash", hashMD5).build();

        String uriString = uri.toUriString();

        ResponseEntity<Events> entity = template.getForEntity(uriString, Events.class);
        System.out.println(entity.getBody().getData().getResults().get(0).getThumbnail().getPath());
        System.out.println(entity.getStatusCode());


        ArrayList<ResultsEvent> resultados = entity.getBody().getData().getResults();

        return resultados;
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
    
    private int randomInt(int limite, int total){
        int intervalo = total / limite;
        Random ran = new Random();
        return ran.nextInt(intervalo);
    }

}