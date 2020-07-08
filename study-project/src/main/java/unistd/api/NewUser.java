package unistd.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新規登録するユーザーです。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUser {

    private String name;

    private String mailAddress;
}
