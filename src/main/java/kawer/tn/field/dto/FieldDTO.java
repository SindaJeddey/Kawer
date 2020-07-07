package kawer.tn.field.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
    private Set<String> amenities;

}
