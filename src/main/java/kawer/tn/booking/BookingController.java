package kawer.tn.booking;


import kawer.tn.booking.dto.BookingDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //Done
    @GetMapping
    public List<BookingDTO> getAllBookings(){
        return bookingService.getAllBookings();
    }

    //Done
    @GetMapping("/user")
    public List<BookingDTO> getUserBookings(@RequestParam(value = "id") Long userId){
        return bookingService.getUserBookings(userId);
    }

    //Done
    @GetMapping("/{bookingId}/user")
    public BookingDTO getUserBookingByID (@PathVariable Long bookingId, @RequestParam(value = "id") Long userId){
        return bookingService.getUserBookingById(bookingId,userId);
    }

    //Done
    @GetMapping("/cancelled/user")
    public List<BookingDTO> getUserCancelledBookings(@RequestParam(value = "id") Long userId){
        return bookingService.getUserCancelledBookings(userId);
    }

    //Done
    @GetMapping("/past/user")
    public List<BookingDTO> getUserPastBookings(@RequestParam(value = "id") Long userId){
        return bookingService.getUserPastBookings(userId);
    }

    //Done
    @GetMapping("/current/user")
    public List<BookingDTO> getUserCurrentBookings(@RequestParam(value = "id") Long userId){
        return bookingService.getUserCurrentBookings(userId);
    }

    //Done
    @PostMapping("/new")
    public BookingDTO bookAField(@RequestParam(value = "user") Long userId,
                                 @RequestParam(value = "field") Long fieldId){
        return bookingService.bookAField(userId,fieldId);
    }

    //Done
    @PutMapping("/{bookingId}")
    public BookingDTO modifyBooking(@RequestBody BookingDTO bookingDTO, @PathVariable Long bookingId){
        return bookingService.updateBooking(bookingDTO,bookingId);
    }

    //Done
    @DeleteMapping("/{bookingId}")
    public void deleteBooking (@PathVariable Long bookingId){
        bookingService.deleteBooking(bookingId);
    }
}
