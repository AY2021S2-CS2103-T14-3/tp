package seedu.address.logic.parser.appointmentparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;

import java.util.stream.Stream;

import seedu.address.logic.commands.appointmentcommands.AddAppointmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.subject.SubjectName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMAIL, PREFIX_SUBJECT_NAME, PREFIX_DATE, PREFIX_TIME_FROM,
                        PREFIX_TIME_TO, PREFIX_LOCATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL, PREFIX_SUBJECT_NAME, PREFIX_DATE,
                PREFIX_TIME_FROM, PREFIX_TIME_TO, PREFIX_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
        }

        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        SubjectName subjectName = ParserUtil.parseSubjectName(argMultimap.getValue(PREFIX_SUBJECT_NAME).get());

        // TODO: Handle timeTo
        String dateString = argMultimap.getValue(PREFIX_DATE).get();
        String timeFromString = argMultimap.getValue(PREFIX_TIME_FROM).get();
        String timeToString = argMultimap.getValue(PREFIX_TIME_TO).get();
        String dateTimeString = dateString + " " + timeFromString;
        AppointmentDateTime dateTime = ParserUtil.parseDateTime(dateTimeString);

        Address location = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_LOCATION).get());

        Appointment appointment = new Appointment(email, subjectName, dateTime, location);

        return new AddAppointmentCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
