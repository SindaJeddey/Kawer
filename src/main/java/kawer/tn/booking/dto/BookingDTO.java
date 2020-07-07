package kawer.tn.booking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {

    private Long id;
    private Calendar reservationDate;
    private Long reservationPrice;
    private boolean cancelled;
    private boolean confirmed;
    private boolean played;
}
