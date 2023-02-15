package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonService {
    @Inject
    EntityManager em;

    public List<Person> getAll() {
        List<Person> pList = em.createNamedQuery("getByNames").getResultList();

        pList.sort(Comparator.comparing(Person::getName));
        return pList;
    }

    public Person getById(long id) {
        List<Person> pList = em.createNamedQuery("getById").getResultList();
        if(pList == null || pList.size() != 1)
            return null;
        return pList.get(0);

    }

    public List<Person> getByLiveLocation() {
        return em.createNamedQuery("getByLiveLocation").getResultList();
    }

    @Transactional
    public void onStart(@Observes StartupEvent event) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getResourceAsStream("/ClientData.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String contents = reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));

            List<Person> personList = mapper.readValue(contents, new TypeReference<List<Person>>(){});

            for (Person person : personList)
                em.merge(person);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
