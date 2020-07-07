package kawer.tn.booking;


import kawer.tn.booking.dto.BookingDTO;
import org.checkerframework.checker.optional.qual.Present;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<BookingDTO> getAllBookings(){
        return bookingService.getAllBookings();
    }


    @GetMapping("/field")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public List<BookingDTO> getFieldBookings(@RequestParam(value = "id") Long fieldId){
        return bookingService.getFieldBookings(fieldId);
    }

    //Done
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<BookingDTO> getUserBookings(@RequestParam(value = "id") Long userId){
        return bookingService.getUserBookings(userId);
    }

    //Done
    @GetMapping("/{bookingId}/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public BookingDTO getUserBookingByID (@PathVariable Long bookingId, @RequestParam(value = "id") Long userId){
        return bookingService.getUserBookingById(bookingId,userId);
    }

    //Done
    @GetMapping("/cancelled/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
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
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<BookingDTO> getUserCurrentBookings(@RequestParam(value = "id") Long userId){
        return bookingService.getUserCurrentBookings(userId);
    }

    //Done
    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public BookingDTO bookAField(@RequestParam(value = "user") Long userId,
                                 @RequestParam(value = "field") Long fieldId){
        return bookingService.bookAField(userId,fieldId);
    }

    //Done
    @PutMapping("/{bookingId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public BookingDTO modifyBooking(@RequestBody BookingDTO bookingDTO, @PathVariable Long bookingId){
        return bookingService.updateBooking(bookingDTO,bookingId);
    }

    //Done
    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void deleteBooking (@PathVariable Long bookingId){
        bookingService.deleteBooking(bookingId);
    }
}
