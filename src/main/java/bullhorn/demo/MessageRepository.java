package bullhorn.demo;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Messages, Long> {


}