package kawer.tn.field;


import kawer.tn.booking.Booking;
import kawer.tn.image.Image;
import kawer.tn.owner.Owner;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotBlank
    private String description;

    @NotBlank
    private String contactNumber;

    @NotNull
    private int capacity;

    @NotNull
    private Long price;

    @ElementCollection
    @MapKeyColumn
    private Set<String> amenities;

    @OneToMany(mappedBy = "field")
    private Set<Booking> bookings;

    @ManyToOne
    private Owner owner;
}
