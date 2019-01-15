package gr.mini.blog.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import gr.mini.blog.components.GlobalComponent;
import gr.mini.blog.forms.RegistrationForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

@CDIView("register")
public class RegisterView extends MVerticalLayout implements View {

    @Inject
    GlobalComponent globalComponent;

    @Inject
    RegistrationForm registrationForm;

    @PostConstruct
    private void onPostConstruct() {
        add(globalComponent, registrationForm);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
