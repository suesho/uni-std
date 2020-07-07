package unistd.domain.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unistd.domain.service.model.User;

/**
 * ユーザーのリポジトリです。
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
