package gr.mini.blog.forms;

import java.util.Optional;
import javax.inject.Inject;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Component;
import gr.mini.blog.interfaces.ArticlesDelegate;
import gr.mini.blog.models.Article;
import gr.mini.blog.services.ArticleService;
import lombok.Setter;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class ArticleForm extends AbstractForm<Article> {
    @Inject
    private ArticleService service;

    @Setter
    private ArticlesDelegate delegate;

    @PropertyId("title")
    private MTextField title = new MTextField("Please enter article's title");

    @PropertyId("content")
    private MTextArea content = new MTextArea("Please enter article's content");

    private void refresh() {
        Optional.ofNullable(delegate).ifPresent(ArticlesDelegate::refresh);
        setEntity(null);
    }

    @Override
    protected Component createContent() {
        setSavedHandler(a -> {
            service.save(a);
            refresh();
        });

        setDeleteHandler(a -> {
            service.delete(a);
            refresh();
        });


        return new MVerticalLayout()
            .withMargin(false)
            .with(title.withFullWidth(), content.withFullWidth(), getToolbar());
    }
}