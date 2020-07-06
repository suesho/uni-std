package unistd.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unistd.service.model.User;

/**
 * ユーザーのリポジトリです。
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
