package br.com.devdojo.javaClient;

import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * consumindo uma API com Spring como Client
 */

public class JavaSprigClientDeleteTest {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/admin/students")
                .basicAuthentication("miguel", "Santiago")
                .build();

        Long id = 6L;

       restTemplate.delete("/{id}",id);


    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return  httpHeaders;
    }

}
