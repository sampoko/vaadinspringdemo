package sampoko.vaadinspringdemo.ui;


import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import sampoko.vaadinspringdemo.model.Gender;
import sampoko.vaadinspringdemo.model.jpa.hibernate.Applicant;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Set;

/**
 * This is a form to add new applicants
 */
public class ApplicationForm extends FormLayout {

    public static final int TEXT_FIELD_MAX_LENGTH = 50;
    public static final int TEXT_AREA_MAX_LENGTH = 1000;
    public static final String dateFormatPattern = "d.M.yyyy";
    public static final String NEW_LINE = "<br/>";


    TextField firstName;
    TextField lastName;
    DateField dateOfBirth;
    NativeSelect gender;
    TextArea motivation;

    Button save = new Button("Save", this::save);


    Applicant applicant;
    BeanItem<Applicant> item;


    public ApplicationForm() {
        this.applicant = new Applicant();
        this.item = new BeanItem<>(this.applicant);
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        firstName = new TextField("First name", item.getItemProperty("firstName"));
        firstName.setMaxLength(TEXT_FIELD_MAX_LENGTH);
        firstName.setRequired(true);
        firstName.setImmediate(true);
        firstName.setNullRepresentation("");

        lastName = new TextField("Last name", item.getItemProperty("lastName"));
        lastName.setMaxLength(TEXT_FIELD_MAX_LENGTH);
        lastName.setRequired(true);
        lastName.setImmediate(true);
        lastName.setNullRepresentation("");

        dateOfBirth  = new DateField("Birth date", item.getItemProperty("dateOfBirth"));
        dateOfBirth.setRequired(true);
        dateOfBirth.setDateFormat(dateFormatPattern);
        dateOfBirth.setImmediate(true);

        gender = new NativeSelect("Gender");
        gender.setPropertyDataSource(item.getItemProperty("gender"));
        gender.setImmediate(true);
        gender.setNullSelectionAllowed(false);
        gender.setRequired(true);
        for (Gender gndr : Gender.values()) {
            gender.addItem(gndr);
            gender.setItemCaption(gndr, StringUtils.capitalize(gndr.name().toLowerCase()));
        }

        motivation = new TextArea("Why are you applying to this job?", item.getItemProperty("motivation"));
        motivation.setMaxLength(TEXT_AREA_MAX_LENGTH);
        motivation.setRequired(true);
        motivation.setImmediate(true);
        motivation.setNullRepresentation("");

        save.setStyleName(Runo.BUTTON_BIG);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        firstName.focus();
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save);
        actions.setSpacing(true);

        addComponents(firstName, lastName, dateOfBirth, gender, motivation, actions);

    }

    public void save(Button.ClickEvent event) {

        try {
            getUI().applicantRepository.save(item.getBean());
            notifySave(this.applicant);
            this.applicant = new Applicant();
            item = new BeanItem<>(this.applicant);
            configureComponents();
            removeAllComponents();
            buildLayout();
        } catch (ConstraintViolationException e) {
                notifyValidationError(e.getConstraintViolations());
        }
    }

    @Override
    public AwesomeUi getUI() {
        return (AwesomeUi) super.getUI();
    }

    private void notifyValidationError(Set<ConstraintViolation<?>> violations) {
        if (CollectionUtils.isEmpty(violations)) {return;}
        StringBuilder s = new StringBuilder();
        violations.stream().forEach(x -> {s.append(NEW_LINE); s.append(x.getMessage());});
        final Notification notification = new Notification(null, s.toString(),
                Notification.Type.WARNING_MESSAGE, true);
        notification.setDelayMsec(5000);
        notification.show(Page.getCurrent());
    }

    private void notifySave(Applicant applicant) {
        StringBuilder s = new StringBuilder();
        s.append(NEW_LINE);
        s.append(NEW_LINE);
        s.append("First name: ");
        s.append(applicant.getFirstName());
        s.append(NEW_LINE);
        s.append(NEW_LINE);
        s.append("Last name: ");
        s.append(applicant.getLastName());
        s.append(NEW_LINE);
        s.append(NEW_LINE);
        s.append("Birth date: ");
        s.append(new SimpleDateFormat(dateFormatPattern).format(this.applicant.getDateOfBirth()));
        s.append(NEW_LINE);
        s.append(NEW_LINE);
        s.append("Gender: ");
        s.append(StringUtils.capitalize(this.applicant.getGender().name().toLowerCase()));
        s.append(NEW_LINE);
        s.append(NEW_LINE);
        s.append("Motivation: ");
        s.append(applicant.getMotivation());


        final Notification notification = new Notification("Successfully saved!",
                s.toString(),
                Notification.Type.HUMANIZED_MESSAGE, true);
        notification.setDelayMsec(10000);
        notification.show(Page.getCurrent());
    }



}
