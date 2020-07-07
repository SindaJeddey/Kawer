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
public class BookingDTOToBookingConverter implements Converter<BookingDTO, Booking> {
    @Override
    @Nullable
    @Synchronized
    public Booking convert(BookingDTO bookingDTO) {
        if(bookingDTO == null)
            return null;
        final Booking booking = new Booking();
        booking.setCancelled(bookingDTO.isCancelled());
        booking.setId(bookingDTO.getId());
        booking.setReservationDate(bookingDTO.getReservationDate());
        booking.setReservationPrice(bookingDTO.getReservationPrice());
        booking.setConfirmed(bookingDTO.isConfirmed());
        booking.setPlayed(bookingDTO.isPlayed());
        return booking;
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
