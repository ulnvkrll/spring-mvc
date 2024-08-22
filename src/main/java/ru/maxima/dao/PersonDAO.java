package ru.maxima.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class PersonDAO {
// Data access object

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> getAllPeople() {

        return jdbcTemplate.query("select * from person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(Long id) {
        return jdbcTemplate.query("select * from person where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream()
                        .findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person (name, age, email) VALUES (?, ?, ?)",
                person.getName(), person.getAge(), person.getMail());

    }

    public void update(Long id, Person editedPerson) {
        jdbcTemplate.update("update person set name = ?, age = ?, email = ? where id = ?)",
                editedPerson.getName(), editedPerson.getAge(), editedPerson.getMail(), id);
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("delete fromm person where id = ?, id");
    }

}
