package springjs.domain.model;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThingRepository extends PagingAndSortingRepository<Thing, UUID> {

}
