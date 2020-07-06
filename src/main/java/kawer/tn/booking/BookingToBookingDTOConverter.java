package kawer.tn.booking;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class BookingToBookingDTOConverter implements Converter<Booking,BookingDTO> {
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
