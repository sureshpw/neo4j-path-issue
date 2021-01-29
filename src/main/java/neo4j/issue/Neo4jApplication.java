package neo4j.issue;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import lombok.Data;

@SpringBootApplication
@ComponentScan(basePackages = "neo4j.issue")
@EnableNeo4jRepositories(basePackages="neo4j.issue")
public class Neo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
	}
	
	@Node("Person")
	@Data
	public static class Person {
	    @GeneratedValue @Id
	    private Long id;
	    String name;

	    @Relationship("HAS_LOCATION")
	    private Location location;
	    
	    @Relationship("HAS_CHILD")
	    private Set<Child> children = new HashSet<>();
	}
	
	@Node("Child")
	@Data
	public static class Child {
	    @GeneratedValue @Id
	    private Long id;
	    private String name;
	    
	    @Relationship("HAS_GRAND_CHILD")
	    private Set<GrandChild> grandChildren = new HashSet<>();
	}
	
	@Node("GrandChild")
	@Data
	public static class GrandChild {
	    @GeneratedValue @Id
	    private Long id;
        private Location location;
	    private String name;
	}
	
	@Node("Location")
	@Data
	public static class Location {
	    @GeneratedValue @Id
	    private Long id;
	    String address;
	}
}
