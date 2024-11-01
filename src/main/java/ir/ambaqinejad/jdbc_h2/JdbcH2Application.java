package ir.ambaqinejad.jdbc_h2;

import ir.ambaqinejad.jdbc_h2.model.Person;
import ir.ambaqinejad.jdbc_h2.repo.PersonRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JdbcH2Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JdbcH2Application.class, args);
        PersonRepo repo = context.getBean(PersonRepo.class);

        // INSERT
        Person person = context.getBean(Person.class);
        person.setId(101);
        person.setFirstName("AmirHosein");
        person.setLastName("Baqinejad");
        person.setAge(27);
        repo.save(person);

        // GET BY ID
        System.out.println(repo.findById(100));
        System.out.println(repo.findById(101));

        // GET ALL
        System.out.println(repo.findAll());

        // UPDATE
        person.setAge(28);
        repo.update(person);
        System.out.println(repo.findById(101));

        // DELETE
        repo.delete(101);
        System.out.println(repo.findAll());
    }

}
