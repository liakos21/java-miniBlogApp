package gr.mini.blog.forms;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Label;
import gr.mini.blog.auth.AuthEvent;
import gr.mini.blog.auth.UserInfo;
import gr.mini.blog.services.UserService;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class LoginForm extends MVerticalLayout {

    @Inject
    private UserService service;

    @Inject
    private UserInfo userInfo;

    @Inject
    private javax.enterprise.event.Event<AuthEvent> event;

    private MTextField uField = new MTextField("Username");
    private MPasswordField pField = new MPasswordField("Password");
    private Label loginLabel = new Label("Enter your credentials to login.");
    private MButton loginBtn = new MButton("Login");

    @PostConstruct
    private void onPostConstruct() {
        loginBtn.withClickShortcut(ShortcutAction.KeyCode.ENTER)
            .withListener(e -> {
                service.auth(uField.getValue(), pField.getValue()).ifPresent(u -> userInfo.setUser(u));
                event.fire(new AuthEvent(AuthEvent.Type.LOGIN));
            });
        add(uField, pField, loginLabel, loginBtn);
    }
}
