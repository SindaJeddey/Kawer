package kawer.tn.owner;

import kawer.tn.field.Field;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String number;
    private String password;
    private List<Field> fields;

}
