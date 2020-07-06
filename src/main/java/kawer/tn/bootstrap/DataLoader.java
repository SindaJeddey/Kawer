package kawer.tn.bootstrap;

import kawer.tn.booking.Booking;
import kawer.tn.booking.BookingService;
import kawer.tn.field.Amenities;
import kawer.tn.field.Capacities;
import kawer.tn.field.Field;
import kawer.tn.field.FieldService;
import kawer.tn.owner.Owner;
import kawer.tn.owner.OwnerService;
import kawer.tn.user.User;
import kawer.tn.user.UserService;
import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final OwnerService ownerService;
    private final FieldService fieldService;
    private final BookingService bookingService;


    public DataLoader(UserService userService,
                      OwnerService ownerService,
                      FieldService fieldService,
                      BookingService bookingService) {
        this.userService = userService;
        this.ownerService = ownerService;
        this.fieldService = fieldService;
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData(){
        User sinda = User.builder()
                .firstName("Sinda")
                .username("Sady")
                .build();
        userService.saveUser(sinda);

        User khaled = User.builder()
                .firstName("Khaled")
                .username("douda")
                .build();
        userService.saveUser(khaled);

        System.out.println("Loading users...");

        Owner ghada = Owner.builder()
                .firstName("Ghada")
                .username("bada")
                .build();

        ownerService.saveOwner(ghada);

        Set<String> amenities = new HashSet<>();
        amenities.add(Amenities.BAR.name());
        amenities.add(Amenities.ARTIFICIAL_TURF.name());

        Field olympysky = Field.builder()
                .capacity(Capacities.HIGH.getCapacity())
                .name("Olympysky")
                .owner(ghada)
                .amenities(amenities)
                .build();
        fieldService.saveField(olympysky);

        amenities.add(Amenities.WIFI.name());
        Field ooreedoo = Field.builder()
                .capacity(Capacities.MEDIUM.getCapacity())
                .name("Ooreedoo")
                .owner(ghada)
                .amenities(amenities)
                .build();
        fieldService.saveField(ooreedoo);

        System.out.println("Loading fields...");

        Set<Field> fieldsGhada = new HashSet<>();
        fieldsGhada.add(olympysky);
        fieldsGhada.add(ooreedoo);
        ghada.setFields(fieldsGhada);
        ownerService.saveOwner(ghada);
        System.out.println("Loading owners....");


        Booking booking = Booking.builder()
                .user(khaled)
                .reservationDate(new Date())
                .field(ooreedoo)
                .build();
        bookingService.saveBooking(booking);

        System.out.println("Loading bookings...");

    }
}
