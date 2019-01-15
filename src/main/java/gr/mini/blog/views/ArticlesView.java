package gr.mini.blog.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.access.AccessControl;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import gr.mini.blog.auth.AuthEvent;
import gr.mini.blog.auth.UserInfo;
import gr.mini.blog.components.GlobalComponent;
import gr.mini.blog.forms.ArticleForm;
import gr.mini.blog.interfaces.ArticlesDelegate;
import gr.mini.blog.models.Article;
import gr.mini.blog.services.ArticleService;
import org.vaadin.viritin.layouts.MVerticalLayout;

@CDIView("articles")
public class ArticlesView extends MVerticalLayout implements View, ArticlesDelegate {

    @Inject
    private ArticleService articleService;

    @Inject
    private AccessControl accessControl;

    @Inject
    private UserInfo userInfo;

    @Inject
    private javax.enterprise.event.Event<AuthEvent> event;

    @Inject
    private ArticleForm form;

    @Inject
    GlobalComponent globalComponent;

    private Grid grid = new Grid();
    private Button addNewBtn = new Button("Add a new article");

    private BeanItemContainer<Article> container = new BeanItemContainer<>(Article.class);

    @PostConstruct
    private void onPostConstruct() {
        add(globalComponent);

        boolean isAuthenticated = accessControl.isUserSignedIn();

        form.setDelegate(this);
        form.setVisible(false);

        grid.setSizeFull();
        grid.setHeightUndefined();
        grid.setColumns("id", "title", "content", "author");
        grid.setContainerDataSource(container);

        grid.addItemClickListener(e -> {
            if (e.isDoubleClick()) {
                Article articleToCheck = (Article) e.getItemId();
                if (isAuthenticated) {
                    if (articleToCheck.getAuthor().getUsername().equals(userInfo.getUser().getUsername())) {
                        form.setVisible(true);
                        form.setEntity((Article) e.getItemId());
                    } else {
                        form.setVisible(false);
                    }
                }
            }
        });

        addNewBtn.addClickListener(e-> {
            form.setVisible(true);
            form.setEntity(new Article());
        });

        with(grid, addNewBtn, form);

        addNewBtn.setVisible(isAuthenticated);
    }

    @Override
    public void refresh() {
        container.removeAllItems();
        container.addAll(articleService.findAll());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        refresh();
    }
}
