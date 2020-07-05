package kawer.tn.field;


import kawer.tn.image.Image;
import kawer.tn.owner.Owner;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field {

    private Long id;
    private String name;
    private String location;
    private String contactNumber;
    private Capacities capacity;
    private Long price;
    private List<String> amenities;
    private List<Image> pictures;
    private Owner owner;
}
