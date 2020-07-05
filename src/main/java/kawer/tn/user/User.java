package kawer.tn.user;

import kawer.tn.field.Field;
import kawer.tn.image.Image;
import kawer.tn.booking.Booking;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Date birthday;

    @Email
    @NotEmpty
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private boolean isSubscribedToNewsLetter;

    @OneToMany(mappedBy = "user")
    private Set<Booking> bookings;

    private Long profilePictureId;

}
