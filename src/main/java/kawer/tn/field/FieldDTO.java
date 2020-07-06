package kawer.tn.field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FieldDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private String contactNumber;
    private int capacity;
    private Long price;

}
