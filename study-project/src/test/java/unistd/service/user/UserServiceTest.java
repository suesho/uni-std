package unistd.service.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import unistd.service.model.User;

/**
 * {@link UserService} のテストです。
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserService service;

    /**
     * {@link UserService#addUser(String, String)} のテストです。
     */
    @Test
    public void addUser() {

        User user = service.addUser("user1", "user1@example.com");

        assertThat(user)
                .isEqualTo(User.builder()
                        .userId(1)
                        .name("user1")
                        .mailAddress("user1@example.com")
                        .build());
    }

    /**
     * {@link UserService#getUsers()} のテストです。
     */
    @Test
    public void getUsers() {

        service.addUser("user2", "aaa@example.com");
        service.addUser("user1", "bbb@example.com");
        service.addUser("user3", "ccc@example.com");

        // 名前順で取得されること
        assertThat(service.getUsers())
                .containsExactly(
                        User.builder()
                                .userId(2)
                                .name("user1")
                                .mailAddress("bbb@example.com")
                                .build(),
                        User.builder()
                                .userId(1)
                                .name("user2")
                                .mailAddress("aaa@example.com")
                                .build(),
                        User.builder()
                                .userId(3)
                                .name("user3")
                                .mailAddress("ccc@example.com")
                                .build());
    }

    /**
     * {@link UserService#updateUser(User)} のテストです。
     */
    @Test
    public void updateUser() {

        User user = service.addUser("user1", "user1@example.com");

        user.setName("hoge");
        user.setMailAddress("hoge@example.com");

        service.updateUser(user);

        assertThat(service.getUsers())
                .hasSize(1)
                .first()
                .isEqualTo(User.builder()
                        .userId(1)
                        .name("hoge")
                        .mailAddress("hoge@example.com")
                        .build());
    }

    /**
     * {@link UserService#deleteUser(int)} のテストです。
     */
    @Test
    public void deleteUser() {

        User user = service.addUser("user1", "user1@example.com");
        service.addUser("user2", "user2@example.com");

        service.deleteUser(user.getUserId());

        assertThat(service.getUsers())
                .hasSize(1)
                .first()
                .isEqualTo(User.builder()
                        .userId(2)
                        .name("user2")
                        .mailAddress("user2@example.com")
                        .build());
    }
}
