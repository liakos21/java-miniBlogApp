package gr.mini.blog.forms;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import gr.mini.blog.auth.UserInfo;
import gr.mini.blog.models.User;
import gr.mini.blog.services.UserService;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class UserForm extends AbstractForm<User> {
    @Inject
    private UserService service;

    @Inject
    private UserInfo userInfo;

    @PropertyId("username")
    private MTextField username = new MTextField("Username");

    @PropertyId("password")
    private MPasswordField password = new MPasswordField("Password");

    private Label updateStatus = new Label("");

    @PostConstruct
    private void onPostConstrunct() {
        setEntity(userInfo.getUser());
    }

    private void refresh() {
        setEntity(userInfo.getUser());
    }

    @Override
    protected Component createContent() {
        setSaveCaption("Update");
        updateStatus.setValue("");
        setSavedHandler(u -> {
            service.save(u);
            refresh();
            updateStatus.setValue("Your information has been updated!");
        });

        return new MVerticalLayout()
            .with(new Label("Update your information."))
            .with(username.withFullWidth(), password.withFullWidth(), getToolbar())
            .with(updateStatus);
    }
}
