package ir.ambaqinejad.jdbc_h2.repo;

import ir.ambaqinejad.jdbc_h2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepo {

    JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public void save(Person person) {
        String sql = "insert into person(id, firstName, lastName, age) values(?,?,?,?)";
        int numRows = template.update(sql, person.getId(), person.getFirstName(), person.getLastName(), person.getAge());
        if (numRows > 0) {
            System.out.println("Inserted " + numRows + " rows into database");
        } else {
            System.out.println("Insert failed");
        }
    }

    public Person findById(int id) {
        String sql = "select * from person where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Person>(Person.class), id);
    }

    public List<Person> findAll() {
        String sql = "select * from person";
        List<Person> people = template.query(sql, (rs, rowNum) -> new Person(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age")));
        return people;
    }

    public void update(Person person) {
        String sql = "update person set firstName = ?, lastName = ?, age = ? where id = ?";
        int numRows = template.update(sql, person.getFirstName(), person.getLastName(), person.getAge(), person.getId());
        if (numRows > 0) {
            System.out.println("Updated " + numRows + " rows into database");
        } else {
            System.out.println("Update failed");
        }
    }

    public void delete(int id) {
        String sql = "delete from person where id = ?";
        int numRows = template.update(sql, id);
        if (numRows > 0) {
            System.out.println("Deleted " + numRows + " rows into database");
        } else {
            System.out.println("Delete failed");
        }
    }
}
