package kawer.tn.booking;

import kawer.tn.booking.dto.BookingDTO;
import kawer.tn.booking.dto.BookingDTOToBookingConverter;
import kawer.tn.booking.dto.BookingToBookingDTOConverter;
import kawer.tn.field.Field;
import kawer.tn.field.FieldRepository;
import kawer.tn.user.User;
import kawer.tn.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final BookingDTOToBookingConverter toBookingConverter;
    private final BookingToBookingDTOConverter toBookingDTOConverter;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          FieldRepository fieldRepository,
                          BookingDTOToBookingConverter toBookingConverter,
                          BookingToBookingDTOConverter toBookingDTOConverter) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.fieldRepository = fieldRepository;
        this.toBookingConverter = toBookingConverter;
        this.toBookingDTOConverter = toBookingDTOConverter;
    }

    public Booking saveBooking (Booking booking){
        return this.bookingRepository.save(booking);
    }

    public List<BookingDTO> getUserBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NullPointerException("User "+userId+" not found"));
        List<BookingDTO> dtos = user.getBookings()
                .stream()
                .map(booking -> toBookingDTOConverter.convert(booking))
                .collect(Collectors.toList());
        return dtos;
    }

    public BookingDTO getUserBookingById(Long bookingId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NullPointerException("User "+userId+" not found"));
        Booking searchedBooking = user.getBookings()
                .stream()
                .filter(booking -> booking.getId() == bookingId)
                .findFirst()
                .orElseThrow(()-> new NullPointerException("Booking "+bookingId+" not found"));
        BookingDTO dto = toBookingDTOConverter.convert(searchedBooking);
        return dto;
    }

    public List<BookingDTO> getUserCancelledBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NullPointerException("User "+userId+" not found"));
        List<BookingDTO> cancelledBookings = user.getBookings()
                .stream()
                .filter(booking -> booking.isCancelled() == true &&
                        booking.isConfirmed() == false &&
                        booking.isPlayed() == false)
                .map(booking -> toBookingDTOConverter.convert(booking))
                .collect(Collectors.toList());
        return cancelledBookings;
    }

    public List<BookingDTO> getUserPastBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NullPointerException("User "+userId+" not found"));
        List<BookingDTO> dtos = user.getBookings()
                .stream()
                .filter(booking -> booking.isPlayed() == true &&
                        booking.isConfirmed() == true &&
                        booking.isCancelled() == false)
                .map(booking -> toBookingDTOConverter.convert(booking))
                .collect(Collectors.toList());
        return dtos;
    }


    public List<BookingDTO> getUserCurrentBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NullPointerException("User "+userId+" not found"));
        List<BookingDTO> dtos = user.getBookings()
                .stream()
                .filter(booking -> booking.isPlayed() == false &&
                        booking.isConfirmed() == true &&
                        booking.isCancelled() == false)
                .map(booking -> toBookingDTOConverter.convert(booking))
                .collect(Collectors.toList());
        return dtos;
    }


    public BookingDTO bookAField(Long userId, Long fieldId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NullPointerException("User "+userId+" not found"));
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(()-> new NullPointerException("Field "+fieldId+" not found"));

        Booking booking = Booking.builder()
                .user(user)
                .field(field)
                .cancelled(false)
                .played(false)
                .confirmed(true)
                .reservationDate(Calendar.getInstance())
                .reservationPrice(20L)
                .build();
        Booking savedBooking = bookingRepository.save(booking);

        Set<Booking> userBookings = user.getBookings();
        userBookings.add(savedBooking);
        user.setBookings(userBookings);
        userRepository.save(user);

        Set<Booking> fieldBookings = field.getBookings();
        fieldBookings.add(savedBooking);
        field.setBookings(fieldBookings);
        fieldRepository.save(field);

        BookingDTO dto = toBookingDTOConverter.convert(savedBooking);
        return dto;
    }

    public BookingDTO updateBooking(BookingDTO bookingDTO, Long bookingId) {
        Booking bookingToUpdate = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new NullPointerException("Booking "+bookingId+" not found"));

        Booking updates = toBookingConverter.convert(bookingDTO);

        if (updates.getReservationDate() != null)
            bookingToUpdate.setReservationDate(updates.getReservationDate());
        if (updates.isCancelled() != bookingToUpdate.isCancelled())
            bookingToUpdate.setCancelled(updates.isCancelled());
        if (updates.isPlayed() != bookingToUpdate.isPlayed())
            bookingToUpdate.setPlayed(updates.isPlayed());
        if (updates.isConfirmed() != bookingToUpdate.isConfirmed() )
            bookingToUpdate.setConfirmed(updates.isConfirmed());
        if ((bookingToUpdate.isCancelled() && bookingToUpdate.isConfirmed()) ||
                (bookingToUpdate.isCancelled() && bookingToUpdate.isPlayed()))
            throw new IllegalArgumentException("Booking can't be cancelled and confirmed/played at the same time");

        Booking savedBooking = bookingRepository.save(bookingToUpdate);
        BookingDTO dto = toBookingDTOConverter.convert(savedBooking);
        return dto;
    }

    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new NullPointerException("Booking "+bookingId+" not found"));
        User user = booking.getUser();
        Field field = booking.getField();

        Set<Booking> userBooking = user.getBookings();
        userBooking.remove(booking);
        user.setBookings(userBooking);
        userRepository.save(user);

        Set<Booking> fieldBooking = field.getBookings();
        fieldBooking.remove(booking);
        field.setBookings(fieldBooking);
        fieldRepository.save(field);

        bookingRepository.deleteById(bookingId);
    }

    public List<BookingDTO> getAllBookings() {
        List<BookingDTO> dtos = bookingRepository.findAll()
                .stream()
                .map(booking -> toBookingDTOConverter.convert(booking))
                .collect(Collectors.toList());

        return dtos;
    }

    public List<BookingDTO> getFieldBookings(Long fieldId) {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(()-> new NullPointerException("Field "+fieldId+" not found"));
        List<BookingDTO> dtos = field.getBookings()
                .stream()
                .map(booking -> toBookingDTOConverter.convert(booking))
                .collect(Collectors.toList());
        return dtos;
    }
}
