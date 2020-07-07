package unistd.domain.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unistd.domain.service.model.TestEntity;

@Repository
public interface TestRepository  extends JpaRepository<TestEntity, Integer>{

}
