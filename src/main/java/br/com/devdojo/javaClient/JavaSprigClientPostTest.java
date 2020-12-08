package br.com.devdojo.javaClient;

import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * consumindo uma API com Spring como Client
 */

public class JavaSprigClientPostTest {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/admin/students")
                .basicAuthentication("miguel", "Santiago")
                .build();

        Student student = new Student();
        student.setNome("Jhon Teste 2  Wick");
        student.setEmail("john@teste.com");

        ResponseEntity<Student> exchange =
                restTemplate.exchange("/", HttpMethod.POST, new HttpEntity<>(student,createJsonHeader()), Student.class);

        System.out.println(exchange);

        Student forObject = restTemplate.postForObject("/", student ,Student.class);
        System.out.println("***** Objetct" + forObject);
        //retorna o objeto encapsulado dentro do ResponseEntity que vem mais informações como o header
        ResponseEntity<Student> forEntity = restTemplate.postForEntity("/", student ,Student.class);
        System.out.println("***** Entity" + forEntity.getBody());//estudante está dentro do body
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return  httpHeaders;
    }

}
