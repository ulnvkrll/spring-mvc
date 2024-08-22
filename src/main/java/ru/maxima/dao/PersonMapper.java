package ru.maxima.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
