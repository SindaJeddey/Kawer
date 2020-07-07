package kawer.tn.booking.dto;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import kawer.tn.booking.Booking;
import kawer.tn.booking.dto.BookingDTO;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class BookingToBookingDTOConverter implements Converter<Booking, BookingDTO> {
    @Override
    @Synchronized
    @Nullable
    public BookingDTO convert(Booking booking) {
        if (booking==null)
            return null;
        final BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCancelled(booking.isCancelled());
        dto.setReservationDate(booking.getReservationDate());
        dto.setReservationPrice(booking.getReservationPrice());
        dto.setConfirmed(booking.isConfirmed());
        dto.setPlayed(booking.isPlayed());
        return dto;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
