package com.marvelquiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.marvelquiz.backend.model.ApiResponse;
import com.marvelquiz.backend.model.character.DataReturnWithCharacter;
import com.marvelquiz.backend.model.comics.DataReturnWithComic;
import com.marvelquiz.backend.model.events.DataReturnWithEvent;
import com.marvelquiz.bean.User;
import com.marvelquiz.bean.character.Character;
import com.marvelquiz.bean.comics.Comic;
import com.marvelquiz.bean.comics.Items;
import com.marvelquiz.bean.events.Event;
import com.marvelquiz.bean.quiz.PerguntasQuiz;
import com.marvelquiz.bean.quiz.Quiz;
import com.marvelquiz.bean.quiz.QuizResult;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


//Main Controller 
@Controller
public class MainController {

    private String scheme = "https";
    private String host = "marvel-web-project.herokuapp.com";
    private int quizCount = 0;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("activeTab", "home");
        return "index";
    }

    @RequestMapping("/login")
    public String login(Map<String, Object> model) {
        model.put("activeTab", "login");
        return "login-presentation";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().setAttribute("username", null);
        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register(Map<String, Object> model) {
        model.put("activeTab", "register");
        return "register-presentation";
    }

    @RequestMapping(value = {"/signIn"}, method = RequestMethod.POST)
    public String registerUser(@RequestParam("txtusername") String username, @RequestParam("txtpassword") String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme("http").host("localhost").port(5000)     
        .path("/api/user")
        .build();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
            map.add("username", user.getUsername());
            map.add("password", user.getPassword());

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


            System.out.println("User: " + user.getUsername() + "Password: " + user.getPassword());

            ResponseEntity<User> userCreated = restTemplate.postForEntity(uri.toString(), request, User.class);

            if (userCreated != null) {
                return "login-presentation";
            } else {
                return "register-validation";
            }
        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
            return "register-validation";
        }
    }

    @RequestMapping("/newPassword")
    public String newPassword(Map<String, Object> model) {
        model.put("activeTab", "login");
        return "update-password";
    }

    @RequestMapping(value = {"/login/page"}, method = RequestMethod.POST)
    public String updatePassword(@RequestParam("usuario") String username, @RequestParam("senhaNovo") String newPassword) {

        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme("http").host("localhost").port(5000)     
        .path("/api/user/newPassword")
        .build();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
            map.add("username", username);
            map.add("password", newPassword);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


            System.out.println("->User: " + username + " ->Password: " + newPassword);

            ResponseEntity<User> userUpadated = restTemplate.postForEntity(uri.toString(), request, User.class);

            if (userUpadated.getStatusCode() == HttpStatus.OK) {
                return "login-presentation";
            } else {
                return "error-page";
            }
        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
            return "error-page";
        }
    }

    final String characterRequestMapping = "/characters";
    @RequestMapping(characterRequestMapping)
    public String chacarterPresentation(Map<String, Object> model) {
        System.out.println("Request: " + characterRequestMapping);
        model.put("activeTab", "characters");
        ArrayList<Character> resultados = getCharacters(20, 1000);
        model.put("records", resultados);
        return "character-presentation";
    }

    final String comicsRequestMapping = "/comics";
    @RequestMapping(comicsRequestMapping)
    public String comicsPresentation(Map<String, Object> model) {
        System.out.println("Request: " + comicsRequestMapping);
        model.put("activeTab", "comics");
        ArrayList<Comic> resultados = getComicsResults(20, 70000);
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
        ArrayList<Event> resultados = getEventResults(10, 30);
        model.put("records", resultados);
        return "event-presentation";
    }

    @RequestMapping("/quiz")
    public String quizPresentation(Map<String, Object> model, HttpServletRequest req){

        if(req.getSession().getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        if (quizCount == 8) {
            quizCount = 0;

            return "redirect:/result";
        }
        
        model.put("activeTab", "quiz");
        
        Quiz quiz = null;

        switch (quizCount) {
            case 0:
                quiz = getQuizImagemNomePersonagem();
            break;
            case 1:
                quiz = getQuizTitlePeriodoEvento();
            break;
            case 2:
                quiz = getQuizImagemTitleEvento();
            break;
            case 3:
                quiz = getQuizDescricaoTitleEvento();
            break;
            case 4:
                quiz = getQuizImagemComecoEvento();
            break;
            case 5:
                quiz = getQuizDescricaoFinalEvento();
            break;
            case 6:
                quiz = getQuizImageTitleComic();
            break;
            case 7:
                quiz = getQuizImagemNomePersonagem();
            break;
        }

        model.put("quiz", quiz);
        quizCount++;
        
        return "quiz-game";
    }

    @RequestMapping("/result")
    public String resultPresentation(Map<String, Object> model) {
        model.put("activeTab", "result");

        QuizResult result = new QuizResult();

        // Retrieve do banco

        model.put("records", result);
        model.put("user", result);
        return "quiz-result";
    }

    private Quiz getQuizImagemNomePersonagem(){
        PerguntasQuiz perguntas = new PerguntasQuiz();

        String pergunta = perguntas.getImagemNomePersonagem();

        ArrayList<Character> character = getCharacters(10, 2000);
        String respostaCerta = character.get(0).getName();

        String conteudo = "" + character.get(0).getThumbnail().getPath() + "/portrait_incredible." + character.get(0).getThumbnail().getExtension();
        boolean conteudoIsImage = true;

        String respostasFixa[] = new String[] {
            character.get(0).getName(),
            character.get(1).getName(),
            character.get(2).getName(),
            character.get(3).getName()
        };

        String[] respostas = randomArray(respostasFixa);

        Quiz quiz = new Quiz(pergunta, respostaCerta, respostas, conteudo, conteudoIsImage);

        return quiz;
    }

    private Quiz getQuizTitlePeriodoEvento(){
        PerguntasQuiz perguntas = new PerguntasQuiz();

        String pergunta = perguntas.getTitlePeriodoEvento();

        ArrayList<Event> events = getEventResults(10, 30);
        String respostaCerta = "From " + events.get(0).getStart().substring(0, 10) + " To " + events.get(0).getEnd().substring(0, 10); 

        String conteudo = events.get(0).getTitle();
        boolean conteudoIsImage = false;

        String respostasFixa[] = new String[] {
            "From " + events.get(0).getStart().substring(0, 10) + " To " + events.get(0).getEnd().substring(0, 10),
            "From " + events.get(1).getStart().substring(0, 10) + " To " + events.get(1).getEnd().substring(0, 10),
            "From " + events.get(2).getStart().substring(0, 10) + " To " + events.get(2).getEnd().substring(0, 10),
            "From " + events.get(3).getStart().substring(0, 10) + " To " + events.get(3).getEnd().substring(0, 10)
        };

        String[] respostas = randomArray(respostasFixa);

        Quiz quiz = new Quiz(pergunta, respostaCerta, respostas, conteudo, conteudoIsImage);

        return quiz;
    }

    private Quiz getQuizImagemTitleEvento(){
        PerguntasQuiz perguntas = new PerguntasQuiz();

        String pergunta = perguntas.getImagemTitleEvento();

        ArrayList<Event> events = getEventResults(10, 20);
        String respostaCerta = "" + events.get(0).getTitle(); 

        String conteudo = "" + events.get(0).getThumbnail().getPath() + "/portrait_incredible." + events.get(0).getThumbnail().getExtension();
        boolean conteudoIsImage = true;

        String respostasFixa[] = new String[] {
            "" + events.get(0).getTitle(),
            "" + events.get(1).getTitle(),
            "" + events.get(2).getTitle(),
            "" + events.get(3).getTitle()
        };

        String[] respostas = randomArray(respostasFixa);

        Quiz quiz = new Quiz(pergunta, respostaCerta, respostas, conteudo, conteudoIsImage);

        return quiz;
    }

    private Quiz getQuizDescricaoTitleEvento(){
        PerguntasQuiz perguntas = new PerguntasQuiz();

        String pergunta = perguntas.getDescricaoTitleEvento();

        ArrayList<Event> events = getEventResults(10, 30);
        String respostaCerta = "" + events.get(0).getTitle();

        String conteudo = "" + events.get(0).getDescription();
        boolean conteudoIsImage = false;

        String respostasFixa[] = new String[] {
            "" + events.get(0).getTitle(),
            "" + events.get(1).getTitle(),
            "" + events.get(2).getTitle(),
            "" + events.get(3).getTitle()
        };

        String[] respostas = randomArray(respostasFixa);

        Quiz quiz = new Quiz(pergunta, respostaCerta, respostas, conteudo, conteudoIsImage);

        return quiz;
    }

    private Quiz getQuizImagemComecoEvento(){
        PerguntasQuiz perguntas = new PerguntasQuiz();

        String pergunta = perguntas.getImagemComecoEvento();

        ArrayList<Event> events = getEventResults(10, 20);
        String respostaCerta = "" + events.get(0).getStart().substring(0, 10);

        String conteudo = "" + events.get(0).getThumbnail().getPath() + "/portrait_incredible." + events.get(0).getThumbnail().getExtension();
        boolean conteudoIsImage = true;

        String respostasFixa[] = new String[] {
            "" + events.get(0).getStart().substring(0, 10),
            "" + events.get(1).getStart().substring(0, 10),
            "" + events.get(2).getStart().substring(0, 10),
            "" + events.get(3).getStart().substring(0, 10)
        };

        String[] respostas = randomArray(respostasFixa);

        Quiz quiz = new Quiz(pergunta, respostaCerta, respostas, conteudo, conteudoIsImage);

        return quiz;
    }

    private Quiz getQuizDescricaoFinalEvento(){
        PerguntasQuiz perguntas = new PerguntasQuiz();

        String pergunta = perguntas.getDescricaoFinalEvento();

        ArrayList<Event> events = getEventResults(4, 90);
        String respostaCerta = "" + events.get(0).getEnd().substring(0, 10);

        String conteudo = "" + events.get(0).getDescription();
        boolean conteudoIsImage = false;

        String respostasFixa[] = new String[] {
            "" + events.get(0).getEnd().substring(0, 10),
            "" + events.get(1).getEnd().substring(0, 10),
            "" + events.get(2).getEnd().substring(0, 10),
            "" + events.get(3).getEnd().substring(0, 10)
        };

        String[] respostas = randomArray(respostasFixa);

        Quiz quiz = new Quiz(pergunta, respostaCerta, respostas, conteudo, conteudoIsImage);

        return quiz;
    }

    private Quiz getQuizImageTitleComic(){
        PerguntasQuiz perguntas = new PerguntasQuiz();

        String pergunta = perguntas.getImageTitleComic();

        ArrayList<Comic> comics = getComicsResults(20, 100000);
        String respostaCerta = "" + comics.get(0).getTitle();

        String conteudo = "" + comics.get(0).getThumbnail().getPath() + "/portrait_incredible." + comics.get(0).getThumbnail().getExtension();
        boolean conteudoIsImage = true;

        String respostasFixa[] = new String[] {
            "" + comics.get(0).getTitle(),
            "" + comics.get(1).getTitle(),
            "" + comics.get(2).getTitle(),
            "" + comics.get(3).getTitle()
        };

        String[] respostas = randomArray(respostasFixa);

        Quiz quiz = new Quiz(pergunta, respostaCerta, respostas, conteudo, conteudoIsImage);

        return quiz;
    }

    private Quiz getQuizTitleAutoresComic(){
        PerguntasQuiz perguntas = new PerguntasQuiz();

        String pergunta = perguntas.getTitleAutoresComic();

        ArrayList<Comic> comics = getComicsResults(20, 100000);
        ArrayList<Items> items = comics.get(0).getCreators().getItems();
        String respostaCerta = "";
        String respostaErrada1 = "";
        String respostaErrada2 = "";
        String respostaErrada3 = "";

        for (int i = 0; i<items.size(); i++) {
            if (i == 0){
                respostaCerta = items.get(i).getName();
            }
            respostaCerta = respostaCerta + ", " + items.get(i).getName();
        }

        String conteudo = "" + comics.get(0).getTitle();
        boolean conteudoIsImage = false;

        ArrayList<Items> itemsErrados1 = comics.get(1).getCreators().getItems();

        for (int i = 0; i<itemsErrados1.size(); i++) {
            if (i == 0){
                respostaErrada1 = itemsErrados1.get(i).getName();
            }
            respostaErrada1 = respostaErrada1 + ", " + itemsErrados1.get(i).getName();
        }

        ArrayList<Items> itemsErrados2 = comics.get(2).getCreators().getItems();

        for (int i = 0; i<itemsErrados2.size(); i++) {
            if (i == 0){
                respostaErrada2 = itemsErrados2.get(i).getName();
            }
            respostaErrada2 = respostaErrada2 + ", " + itemsErrados2.get(i).getName();
        }

        ArrayList<Items> itemsErrados3 = comics.get(3).getCreators().getItems();

        for (int i = 0; i<itemsErrados3.size(); i++) {
            if (i == 0){
                respostaErrada1 = itemsErrados3.get(i).getName();
            }
            respostaErrada1 = respostaErrada1 + ", " + itemsErrados3.get(i).getName();
        }

        String respostasFixa[] = new String[] {
            "" + respostaCerta,
            "" + respostaErrada1,
            "" + respostaErrada2,
            "" + respostaErrada3
        };

        String[] respostas = randomArray(respostasFixa);

        Quiz quiz = new Quiz(pergunta, respostaCerta, respostas, conteudo, conteudoIsImage);

        return quiz;
    }

    private ArrayList<Character> getCharacters(int limite, int total) {
        // int limite = 10;
        // int total = 1493;

        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme(scheme).host(host)
        // .scheme("http").host("localhost").port(5000)
        .path("/api/characters")
        .queryParam("limit", limite)
        .queryParam("offset", randomInt(limite, total))
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

    private ArrayList<Comic> getComicsResults(int limite, int total) {
        //limite de records por página
        // int limite = 10;
        //total de records na api
        // int total = 70000;
        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme(scheme).host(host)
        // .scheme("http").host("localhost").port(5000)
        .path("/api/comics")
        .queryParam("limit", limite)
        .queryParam("offset", randomInt(limite, total))
        .build();
        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<DataReturnWithComic> entity;
            entity = template.getForEntity(uri.toUriString(), DataReturnWithComic.class);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            System.out.println(ts + " " + entity.getStatusCode());
            return entity.getBody().getResults();
        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    private ArrayList<Event> getEventResults(int limite, int total) {
        //limite de records por página
        // int limite = 10;
        //total de records na api
        // int total = 75;

        UriComponents uri = UriComponentsBuilder.newInstance()
        .scheme(scheme).host(host)
        // .scheme("http").host("localhost").port(5000)     
        .path("/api/events")
        .queryParam("limit", limite)
        .queryParam("offset", randomInt(limite, total))
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

    private int randomInt(int limite, int total){
        int intervalo = total / limite;
        Random ran = new Random();
        return ran.nextInt(intervalo);
    }

    private String[] randomArray (String[] respostas){

        String[] respostasRandom = {"", "", "", ""};

        Random ran = new Random();

        for (int i = 0; i < 4; i++){
            int index = ran.nextInt(4);

            while(respostasRandom[index] != "") {
                index = ran.nextInt(4);
            }
            respostasRandom[index] = respostas[i];
        }

        return respostasRandom;
    }

}