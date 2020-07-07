package kawer.tn.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String username;
    private String password;
    private boolean isSubscribedToNewsLetter;
}
