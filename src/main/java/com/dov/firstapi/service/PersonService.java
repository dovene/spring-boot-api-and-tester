package com.dov.firstapi.service;

import com.dov.firstapi.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private List<Person> personList = Arrays.asList(new Person(2, "ret", "tre", 4),
            new Person(1, "ret", "tre", 4));

    public Person addPerson(final Person personToAdd) {
        List<Person> identicalPersons = personList.stream().filter(new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return personToAdd.getId() == person.getId();
            }
        }).collect(Collectors.toList());
        ArrayList<Person> temp = new ArrayList<>(personList);
        if (identicalPersons.size() == 0) {
            temp.add(personToAdd);
            personList = new ArrayList<>(temp);
            return personToAdd;
        } else {
            return null;
        }
    }

    public List<Person> getAll() {
        return personList;
    }

    public Person getOneById(int id) {
        List<Person> matchingList = personList.stream().filter(person -> id == person.getId()).collect(Collectors.toList());
        return (matchingList.size() == 0) ? null : matchingList.get(0);
    }

    public void removePersonById(int id) {
        personList = personList.stream().filter(new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return id != person.getId();
            }
        }).collect(Collectors.toList());
    }

    public Person updatePersonById(int id, final Person newPerson) {
        personList.forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                if (person.getId() == id) {
                    person.setLastName(newPerson.getLastName());
                    person.setFirstName(newPerson.getFirstName());
                    person.setAge(newPerson.getAge());
                }
            }
        });
        return getOneById(id);
    }


}
