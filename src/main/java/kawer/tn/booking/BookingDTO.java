package kawer.tn.booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {

    private Long id;
    private Date reservationDate;
    private Long reservationPrice;
    private boolean cancelled;
}
