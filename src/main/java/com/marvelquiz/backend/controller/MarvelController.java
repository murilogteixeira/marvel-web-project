package com.marvelquiz.backend.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import com.google.gson.Gson;
import com.marvelquiz.backend.model.Credenciais;
import com.marvelquiz.backend.model.DataReturn;
import com.marvelquiz.backend.model.character.MarvelReturnWithCharacter;
import com.marvelquiz.backend.model.comics.MarvelReturnWithComic;
import com.marvelquiz.backend.model.events.MarvelReturnWithEvent;
import com.marvelquiz.bean.character.Character;
import com.marvelquiz.bean.comics.Comic;
import com.marvelquiz.bean.errors.RestClientError;
import com.marvelquiz.bean.events.Event;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class MarvelController {

    private Credenciais c = new Credenciais();

    private String scheme = "https";
    private String host = "gateway.marvel.com";
    private int port = 443;
    private String path = "v1/public/";

    final String characterRequestMapping = "/api/characters";
    @RequestMapping(value = characterRequestMapping, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DataReturn<Character>> characters(@RequestParam Optional<Integer> limit) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(ts + " Request : " + characterRequestMapping);

        return new ResponseEntity<DataReturn<Character>>(getCharacters(limit), HttpStatus.OK);
    }

    final String comicsRequestMapping = "/api/comics";
    @RequestMapping(value = comicsRequestMapping, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataReturn<Comic>> comics(Optional<Integer> limit) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(ts + " Request : " + comicsRequestMapping);
        
        return new ResponseEntity<DataReturn<Comic>>(getComics(limit), HttpStatus.OK);
    }

    final String eventsRequestMapping = "/api/events";
    @RequestMapping(value = eventsRequestMapping, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataReturn<Event>> events(Optional<Integer> limit) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(ts + " Request : " + eventsRequestMapping);
        
        return new ResponseEntity<DataReturn<Event>>(getEvents(limit), HttpStatus.OK);
    }

    private DataReturn<Character> getCharacters(Optional<Integer> limit) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        c.setTs(ts);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
        .scheme(scheme).host(host).port(port)
        .path(path + "characters")
        .queryParam("ts", c.getTs())
        .queryParam("apikey", c.getPublicKey())
        .queryParam("hash", c.getHash())
        .queryParam("limit", 50)
        .queryParam("offset", randomInt(50, 1493));

        UriComponents uri = uriBuilder.build();

        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<MarvelReturnWithCharacter> entity;
            entity = template.getForEntity(uri.toUriString(), MarvelReturnWithCharacter.class);

            if(limit.isPresent()) {
                ArrayList<Character> characters = new ArrayList<>();
                int size = entity.getBody().getData().getResults().size();
                for(int i = 0; i < limit.get() ; i++) {
                    int index = new Random().nextInt(size);
                    Character c = entity.getBody().getData().getResults().get(index);
                    if(characters.contains(c) || c.getThumbnail().getPath().contains("image_not_available") || c.getThumbnail().getPath().contains("4c002e0305708")){
                        i--;
                    } else {
                        characters.add(c);
                    }
                }
                entity.getBody().getData().setResults(characters);
            }

            HttpStatus status = entity.getStatusCode();
            System.out.println(ts + " " + status);
            return entity.getBody().getData();
        } catch (RestClientException e) {
            RestClientError error = errorMessageToRestClientError(e.getMessage());
            System.out.println(error);
            // charactersReturn.setError(error);
            return null;
        }
    }

    private DataReturn<Comic> getComics(Optional<Integer> limit) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        c.setTs(ts);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
        .scheme(scheme).host(host).port(port)
        .path(path + "comics")
        .queryParam("ts", c.getTs())
        .queryParam("apikey", c.getPublicKey())
        .queryParam("hash", c.getHash())
        .queryParam("format", "comic")
        .queryParam("noVariants", "true")
        .queryParam("limit", 30)
        .queryParam("offset", randomInt(50, 32589));

        UriComponents uri = uriBuilder.build();

        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<MarvelReturnWithComic> entity;
            entity = template.getForEntity(uri.toUriString(), MarvelReturnWithComic.class);

            if(limit.isPresent()) {
                ArrayList<Comic> comics = new ArrayList<>();
                int size = entity.getBody().getData().getResults().size();
                for(int i = 0; i < limit.get(); i++) {
                    int index = new Random().nextInt(size);
                    Comic c = entity.getBody().getData().getResults().get(index);
                    if(comics.contains(c) || c.getThumbnail().getPath().contains("image_not_available") || c.getThumbnail().getPath().contains("4c002e0305708")) {
                        i--;
                    } else {
                        comics.add(c);
                    }
                }
                entity.getBody().getData().setResults(comics);
            }

            HttpStatus status = entity.getStatusCode();
            System.out.println(ts + " " + status);
            return entity.getBody().getData();
        } catch (RestClientException e) {
            RestClientError error = errorMessageToRestClientError(e.getMessage());
            System.out.println(error);
            // comicsReturn.setError(error);
            return null;
        }
    }

    private DataReturn<Event> getEvents(Optional<Integer> limit) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        c.setTs(ts);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
        .scheme(scheme).host(host).port(port)
        .path(path + "events")
        .queryParam("ts", c.getTs())
        .queryParam("apikey", c.getPublicKey())
        .queryParam("hash", c.getHash())
        .queryParam("limit", 75);

        UriComponents uri = uriBuilder.build();

        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<MarvelReturnWithEvent> entity;
            entity = template.getForEntity(uri.toUriString(), MarvelReturnWithEvent.class);

            if(limit.isPresent()) {
                ArrayList<Event> events = new ArrayList<>();
                int size = entity.getBody().getData().getResults().size();
                for(int i = 0; i < limit.get(); i++) {
                    int index = new Random().nextInt(size);
                    Event e = entity.getBody().getData().getResults().get(index);
                    if(events.contains(e) || e.getStart() == null || e.getStart().contains("null")) {
                        i--;
                    } else {
                        events.add(e);
                    }
                }
                entity.getBody().getData().setResults(events);
            }

            HttpStatus status = entity.getStatusCode();
            System.out.println(ts + " " + status);
            return entity.getBody().getData();
        } catch (RestClientException e) {
            RestClientError error = errorMessageToRestClientError(e.getMessage());
            System.out.println(error);
            // comicsReturn.setError(error);
            return null;
        }
    }

    private RestClientError errorMessageToRestClientError(String error) {
        String [] errorSplited = error.split(":");
        String json = "{\"status\":";
        for (int i = 0; i < errorSplited.length; i++) {
            if(i == 0) {
                json += "\"" + errorSplited[i] + "\",";
            } else if(i == 1) {
                json += "\"description\":" + errorSplited[i] + ":";
            } else if (i == errorSplited.length - 1) {
                json += errorSplited[i];
            } else {
                json += errorSplited[i] + ":";
            }
        }
        json += "}";
        RestClientError restClientError = new Gson().fromJson(json, RestClientError.class);
        return restClientError;
    }

    private int randomInt(int limite, int total) {
        int intervalo = total / limite;
        return new Random().nextInt(intervalo);
    }

}