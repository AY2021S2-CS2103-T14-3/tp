package seedu.address.ui;

import java.util.Comparator;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.subject.TutorSubject;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TutorCard extends UiPart<Region> {

    private static final String FXML = "TutorListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label subject;
    @FXML
    private FlowPane subjects;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TutorCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        subject.setText("Subjects: ");
        person.getSubjectList().asUnmodifiableObservableList().stream()
                .filter(subject -> Objects.nonNull(subject))
                .forEach(subject -> displaySubjectDetails(subject));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void displaySubjectDetails(TutorSubject subject) {
        subjects.getChildren().add(new Label(subject.getName().name));
        subjects.getChildren().add(new Label("  Level: " + subject.getLevel().level));
        subjects.getChildren().add(new Label("  Rate: SGD" + subject.getRate().rate + "/hr"));
        subjects.getChildren().add(new Label("  Experience: " + subject.getExperience().experience + "years"));
        subjects.getChildren().add(new Label("  Qualification: " + subject.getQualification().qualification));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorCard)) {
            return false;
        }

        // state check
        TutorCard card = (TutorCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
