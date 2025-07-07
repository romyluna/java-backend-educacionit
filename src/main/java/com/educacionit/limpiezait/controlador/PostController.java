package com.educacionit.limpiezait.controlador;

import com.educacionit.limpiezait.dominio.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/post-placeholder")
public class PostController {

    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;
    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public PostController(ObjectMapper mapper, RestTemplate restTemplate) {
        this.mapper = mapper;
        this.restTemplate = restTemplate;
}

        @GetMapping("/{id}")
        public Post getJsonToObjectPost ( @PathVariable int id) throws JsonProcessingException {
            String endpoint = BASE_URL.concat("/posts/").concat(String.valueOf(id));
            String json = this.restTemplate.getForObject(endpoint, String.class);
            System.out.println("JSON OBTENIDO;" + json);
            //transformar json a objeto
            Post post = this.mapper.readValue(json, Post.class);
            return post;
        }

    }


