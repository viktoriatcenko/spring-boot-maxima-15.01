package ru.maxima.springboottest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.springboottest.models.*;
import ru.maxima.springboottest.repositories.*;
import ru.maxima.springboottest.service.*;

import java.util.List;

// select * from person where name like 'V%'
@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);
    List<Person> findPeopleByNameLike(String like);

    Person findByEmail(String email);

    List<Person> findAllByAgeAfter(Integer age);

    List<Person> findPeopleByEmailEndingWith(String like);

    List<Person> findPeopleByAgeBetween(Integer from, Integer until);

    List<Person> findPeopleByAge(Integer age);

    List<Person> findAllByNameOrderByAge(String name);

    List<Person> findPeopleByNameOrEmail(String name, String email);

    List<Person> findPeopleByEmailOrderByAgeDesc(String email);




}
