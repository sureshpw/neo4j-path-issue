package neo4j.issue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import neo4j.issue.Neo4jApplication.Child;
import neo4j.issue.Neo4jApplication.GrandChild;
import neo4j.issue.Neo4jApplication.Person;
import neo4j.issue.Neo4jApplication.Location;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Neo4jApplication.class)
@TestPropertySource(properties = {
  "spring.neo4j.authentication.username=neo4j",
  "spring.neo4j.authentication.password=password",
})
class Neo4jApplicationTests {
    @Autowired
    private PersonRepository repo;

    @BeforeEach
    public void setup() {
        Person person = new Person();
        person.setName("person-1");

        Child child = new Child();
        child.setName("child-1");
        
        GrandChild gchild = new GrandChild();
        gchild.setName("gchild-1");
        
        Location location = new Location();
        location.setAddress("123 Main Street");
        
        Location location2 = new Location();
        location2.setAddress("789 Other Street");
        
        person.setLocation(location);
        person.getChildren().add(child);
        
        child.getGrandChildren().add(gchild);

        gchild.setLocation(location2);

        repo.save(person);
        
    }

    @Test
    public void testGet() {
        System.out.println("\n\n>>>From Find All>>>" + repo.findAll() + "\n\n");
        System.out.println("\n\n>>>From APOC Path>>>" + repo.getByPath("person-1") + "\n\n");
    }
}
