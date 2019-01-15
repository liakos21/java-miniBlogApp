package gr.mini.blog.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import gr.mini.blog.components.GlobalComponent;
import gr.mini.blog.forms.UserForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

@CDIView("userinfo")
public class UserinfoView extends MVerticalLayout implements View {

    @Inject
    GlobalComponent globalComponent;

    @Inject UserForm userForm;

    @PostConstruct
    private void onPostConstruct() {
        add(globalComponent, userForm);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
