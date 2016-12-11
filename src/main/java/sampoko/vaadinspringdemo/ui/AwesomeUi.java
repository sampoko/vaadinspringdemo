package sampoko.vaadinspringdemo.ui;


import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;
import sampoko.vaadinspringdemo.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Theme(Runo.THEME_NAME)
@SpringUI
public class AwesomeUi extends UI {

    @Autowired
    ApplicantRepository applicantRepository;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);
        Component form = new ApplicationForm();
        root.addComponent(form);
        root.setExpandRatio(form, 1.0f);
        root.setResponsive(true);
    }
}

