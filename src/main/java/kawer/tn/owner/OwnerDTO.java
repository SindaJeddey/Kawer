package kawer.tn.owner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String number;
}
