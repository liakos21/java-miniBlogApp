package gr.mini.blog.forms;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import gr.mini.blog.models.User;
import gr.mini.blog.services.UserService;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class RegistrationForm extends AbstractForm<User> {
    @Inject
    private UserService service;

    @PropertyId("username")
    private MTextField username = new MTextField("Username");

    @PropertyId("password")
    private MPasswordField password = new MPasswordField("Password");

    private void refresh() {
        setEntity(new User());
    }

    @PostConstruct
    private void onPostConstrunct() {
        setEntity(new User());
    }

    @Override
    protected Component createContent() {
        setSaveCaption("Create Account");
        setSavedHandler(u -> {
            service.save(u);
            refresh();
        });

        return new MVerticalLayout()
            .with(new Label("Enter your desired credentials to register."))
            .with(username.withFullWidth(), password.withFullWidth(), getToolbar());
    }
}
