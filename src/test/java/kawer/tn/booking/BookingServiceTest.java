package kawer.tn.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    BookingRepository bookingRepository;

    @InjectMocks
    BookingService bookingService;

    Booking booking1;

    @BeforeEach
    void setUp() {
        booking1 = Booking.builder()
                .id(1L)
                .reservationPrice(300L)
                .build();
    }

    @Test
    void saveOwner() {
        when(bookingRepository.save(any())).thenReturn(booking1);
        Booking bookingToSave = bookingService.saveBooking(booking1);
        assertNotNull(bookingToSave);
        assertEquals(bookingToSave.getId(),1L);
    }
}