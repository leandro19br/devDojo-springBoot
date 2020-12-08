package br.com.devdojo.javaClient;

import br.com.devdojo.model.PageableResponse;
import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * consumindo uma API com Spring como Client
 */

public class JavaSprigClientTest {

    public static void main(String[] args) {

                RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/protected/students")
                .basicAuthentication("miguel", "Santiago")
                .build();
                /*
        //fazendo uma chamada rest retorna o objeto puro, 1 é a vaiavel passada na URL
        Student forObject = restTemplate.getForObject("/{id}", Student.class, 1);
        System.out.println("***** Objetct" + forObject);
        //retorna o objeto encapsulado dentro do ResponseEntity que vem mais informações como o header
        ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, 1);
        System.out.println("***** Entity" + forEntity.getBody());//estudante está dentro do body

        Student[] forObjects = restTemplate.getForObject("/", Student[].class, 1);
        System.out.println("***** Array" + Arrays.toString(forObjects));
        //Utiizando Exchange para retornar a lista de estudant
        */

        ResponseEntity<PageableResponse<Student>> exchange =
                restTemplate.exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Student>>() {
        });

        System.out.println(exchange.getBody().getContent());


    }

}
