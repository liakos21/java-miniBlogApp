package gr.mini.blog;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.cdi.access.AccessControl;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import gr.mini.blog.auth.AuthEvent;
import gr.mini.blog.forms.LoginForm;
import gr.mini.blog.forms.RegistrationForm;
import gr.mini.blog.services.UserService;
import org.vaadin.viritin.layouts.MPanel;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("valo")
@CDIUI("")
//@PreserveOnRefresh
public class MainUI extends UI {

    @Inject
    private AccessControl accessControl;

    @Inject
    private UserService userService;

    @Inject
    private CDIViewProvider viewProvider;

    @Inject
    private RegistrationForm regForm;

    @Inject
    private LoginForm loginForm;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(viewProvider);

        loadUI();

    }

    private void loadUI() {
        if (!accessControl.isUserSignedIn()) {
            VerticalLayout panelWrapper = new VerticalLayout();
            panelWrapper.setSizeFull();
            MPanel formPanel = new MPanel(loginForm).withWidth(250, Unit.PIXELS);
            panelWrapper.addComponent(formPanel);
            panelWrapper.setComponentAlignment(formPanel, Alignment.MIDDLE_CENTER);
            setContent(panelWrapper);
        } else {
            getNavigator().navigateTo("articles");
        }
    }

    private void userLogin(@Observes AuthEvent event) {
        if (event.getType() == AuthEvent.Type.LOGIN) {
            loadUI();
        } else{
            VaadinSession.getCurrent().getSession().invalidate();
            VaadinSession.getCurrent().close();
            Page.getCurrent().setUriFragment("", false);
            Page.getCurrent().reload();
        }

    }
}
