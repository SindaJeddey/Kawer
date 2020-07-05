package kawer.tn.user;

import kawer.tn.image.Image;
import kawer.tn.booking.Booking;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String username;
    private String password;
    private boolean isSubscribedToNewsLetter;
    private List<Booking> bookings;
    private Image profilePicture;

}
