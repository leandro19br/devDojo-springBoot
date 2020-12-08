package br.com.devdojo.javaClient;

import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * consumindo uma API com Spring como Client
 */

public class JavaSprigClientPutTest {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/admin/students")
                .basicAuthentication("miguel", "Santiago")
                .build();

        Student student = new Student();
        student.setId(6L);
        student.setNome("Jhon Teste Put 6 Wick");
        student.setEmail("john@teste.com");

       restTemplate.put("/",student);


    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return  httpHeaders;
    }

}
