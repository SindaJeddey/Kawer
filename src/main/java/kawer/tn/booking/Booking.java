package kawer.tn.booking;

import kawer.tn.field.Field;
import kawer.tn.user.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    private Long id;
    private User user;
    private Field field;
    private Date reservationDate;
    private Long reservationPrice;
    private boolean isCancelled;

}
