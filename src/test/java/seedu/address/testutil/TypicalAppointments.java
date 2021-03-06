package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment MATHS_APPOINTMENT = new AppointmentBuilder()
            .withEmail("alice@example.com")
            .withSubject("Mathematics").withDateTime("2021-03-24 10:00AM")
            .withAddress("Jurong West").build();
    public static final Appointment SCIENCE_APPOINTMENT = new AppointmentBuilder()
            .withEmail("johnd@example.com")
            .withSubject("Science").withDateTime("2021-03-27 12:00PM")
            .withAddress("Clementi").build();
    public static final Appointment ENGLISH_APPOINTMENT = new AppointmentBuilder()
            .withEmail("heinz@example.com")
            .withSubject("English").withDateTime("2021-03-28 11:00AM")
            .withAddress("wall street").build();

    public static final String KEYWORD_MATCHING_ALEX = "Alice"; // A keyword that matches ALICE

    private TypicalAppointments() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AppointmentBook getTypicalAppointmentBook() {
        AppointmentBook ab = new AppointmentBook();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(MATHS_APPOINTMENT, SCIENCE_APPOINTMENT));
    }
}
