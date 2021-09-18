package com.dov.firstapi.controller;

import com.dov.firstapi.model.Person;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tester")
public class TesterController {

    @GetMapping
    public Iterable<Person> getPersons() {
        String baseApiUrl = "http://localhost:1000/";

        String getEmployeesUrl = baseApiUrl + "persons";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Person>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Person>>() {}
        );


        System.out.println("Get Persons call " + response.getStatusCode().toString());

        return response.getBody();
    }

    @GetMapping("/create")
    public Person createEmployee() {
        String baseApiUrl = "http://localhost:1000/";
        String createEmployeeUrl = baseApiUrl + "/persons";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> request = new HttpEntity<Person>(new Person(4,"r","se",87));
        ResponseEntity<Person> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Person.class);

        System.out.println("Create Person call " + response.getStatusCode().toString());

        return response.getBody();
    }
}
