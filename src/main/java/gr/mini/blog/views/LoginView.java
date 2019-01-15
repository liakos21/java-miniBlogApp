package gr.mini.blog.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import gr.mini.blog.components.GlobalComponent;
import gr.mini.blog.forms.LoginForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

@CDIView("login")
public class LoginView extends MVerticalLayout implements View {

    @Inject
    GlobalComponent globalComponent;

    @Inject LoginForm loginForm;

    @PostConstruct
    private void onPostConstruct() {
        add(globalComponent, loginForm);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
