package neo4j.issue;

import java.util.Collection;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import neo4j.issue.Neo4jApplication.Person;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
    @Query("match(p: Person { name: $name }) "
            + "call apoc.path.expand(p, 'HAS_CHILD|HAS_LOCATION|HAS_GRAND_CHILD', null, 1, 3) "
            + "yield path "
            + "return collect(path)")    
    Collection<Person> getByPath(String name);
}
