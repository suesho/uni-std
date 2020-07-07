package unistd.domain.service.user;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import unistd.domain.service.model.User;
import unistd.domain.service.repository.UserRepository;

/**
 * ユーザー情報を扱うServiceです。
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserRepository userRepository;

    /**
     * ユーザー情報を追加します。
     * @param userName ユーザー名
     * @param mailAddress メールアドレス
     * @return 追加したユーザー情報
     */
    public User addUser(String userName, String mailAddress) {

        return userRepository.save(
                User.builder()
                        .name(userName)
                        .mailAddress(mailAddress)
                        .build());
    }

    /**
     * ユーザー情報一覧を取得します。
     * @return ユーザー情報一覧
     */
    public List<User> getUsers() {

        return userRepository.findAll(new Sort("name"));
    }

    /**
     * ユーザー情報を更新します。
     * @param user ユーザー情報
     */
    public void updateUser(User user) {

        userRepository.save(user);
    }

    /**
     * ユーザー情報を削除します。
     * @param userId ユーザーID
     */
    public void deleteUser(int userId) {

        userRepository.delete(userId);
    }
}
