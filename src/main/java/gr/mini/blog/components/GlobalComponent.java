package gr.mini.blog.components;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.cdi.access.AccessControl;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import gr.mini.blog.MainUI;
import gr.mini.blog.auth.AuthEvent;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class GlobalComponent extends MVerticalLayout {

    @Inject
    private AccessControl accessControl;

    @Inject
    private javax.enterprise.event.Event<AuthEvent> event;

    @Inject
    private CDIViewProvider viewProvider;

    @Inject
    private MainUI mainUI;

    private MHorizontalLayout menuWrapper = new MHorizontalLayout();
    private MButton loginBtn = new MButton("Login").withListener(e -> goToLogin());
    private MButton logoutBtn = new MButton("Logout").withListener(e -> event.fire(new AuthEvent(AuthEvent.Type.LOGOUT)));
    private MButton registerBtn = new MButton("Register").withListener(e -> mainUI.getNavigator().navigateTo("register"));
    private MButton articlesBtn = new MButton("Articles").withListener(e -> mainUI.getNavigator().navigateTo("articles"));
    private MButton userInfoBtn = new MButton("User Info").withListener(e -> mainUI.getNavigator().navigateTo("userinfo"));

    @PostConstruct
    private void onPostConstruct() {
        boolean isAuthenticated = accessControl.isUserSignedIn();
        menuWrapper.add(loginBtn, logoutBtn, registerBtn, articlesBtn, userInfoBtn);
        add(menuWrapper);
        setComponentAlignment(menuWrapper, Alignment.TOP_CENTER);
        loginBtn.setVisible(!isAuthenticated);
        logoutBtn.setVisible(isAuthenticated);
        registerBtn.setVisible(!isAuthenticated);
        userInfoBtn.setVisible(isAuthenticated);
    }

    private void goToLogin() {
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
        Page.getCurrent().setUriFragment("", false);
        Page.getCurrent().reload();
    }
}
