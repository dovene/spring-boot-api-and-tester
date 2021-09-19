package com.dov.firstapi.controller;

import com.dov.firstapi.exceptionhandler.ResourceNotFoundException;
import com.dov.firstapi.model.Person;
import com.dov.firstapi.service.PersonService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    private List<Person> getPersons(){
        return personService.getAll();
    }

    @GetMapping(path="/{id}", produces = "application/json")
    private Person getPersonById(@PathVariable("id") int id) throws ResourceNotFoundException {
        Person person = personService.getOneById(id);
        if (person == null){
            throw new ResourceNotFoundException();
        }
        return personService.getOneById(id);
    }

    // just for testing purpose to
    @GetMapping("/delete/{id}")
    private List<Person> deleteUsingGetById(@PathVariable("id") int id){
         personService.removePersonById(id);
         return personService.getAll();
    }

    @DeleteMapping("/{id}")
    private List<Person> deleteById(@PathVariable("id") int id){
        personService.removePersonById(id);
        return personService.getAll();
    }

    @PostMapping
    private Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @PutMapping
    private Person updateById(@PathVariable("id") int id, @RequestBody Person person){
        return personService.updatePersonById(id, person);
    }


}
