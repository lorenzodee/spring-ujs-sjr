package springjs.domain.model;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ThingRepository extends CrudRepository<Thing, UUID> {
	
	Page<Thing> findAll(Pageable pageable);

}
