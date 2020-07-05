package kawer.tn.booking;

import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking saveBooking (Booking booking){
        return this.bookingRepository.save(booking);
    }
}
