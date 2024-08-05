package ru.maxima.dao;

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


    public List<Person> getAllPeople() {
        List<Person> allPeople = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setMail(resultSet.getString("email"));
                allPeople.add(person);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allPeople;
    }

    public Person findById(Long id) {
        Person person = null;
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from person where id = ?" );
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

//            Statement statement = connection.createStatement();
//            String SQL = "select * from person where id = " + id;
//            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setMail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public void save(Person person) {
//        Person maxByAge = getAllPeople()
//                .stream()
//                .max(Comparator.comparing(Person::getAge))
//                .orElseThrow(NoSuchElementException::new);
//        Long nextId = (maxByAge.getId() + 1);
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("insert into person(name, age, email) values (?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getMail());
            preparedStatement.executeUpdate();


//            Statement statement = connection.createStatement();
//            String SQL = "insert into person(id, name, age, email) values(" +
//                    nextId + ", '" +
//                    person.getName() + "' ," +
//                    person.getAge() + ", '" +
//                    person.getMail() + "'" +
//                    ")";
//            statement.executeUpdate(SQL);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
        }
    }

    public void update(Long id, Person editedPerson) {
        Long nextId = (long) (getAllPeople().size() +1);
        try {
            Statement statement = connection.createStatement();
            String SQL = "update person " +
                    "set name = '"+ editedPerson.getName() + "'" +
                    ", age = " + editedPerson.getAge() +
                    ", email = '" + editedPerson.getMail() + "'" +
                    " where id = " +id;
            statement.executeUpdate(SQL);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteById(Long id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "delete from person where id = " +id;
            statement.executeUpdate(SQL);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
