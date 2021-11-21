package com.notion.events;

import com.notion.exceptions.page.InvalidPageException;
import com.notion.exceptions.page.PageNotFound;
import com.notion.model.Page;
import com.notion.repositry.PagesRepository;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.notion.constants.Constants.PageException.PARENT_PAGE_ID_NOT_FOUND;
import static com.notion.constants.Constants.PageException.TITLE_EMPTY_EXCEPTION;

@Component
public class PageListener extends AbstractMongoEventListener<Page> {

    private final PagesRepository pagesRepository;

    public PageListener(PagesRepository pagesRepository) {
        this.pagesRepository = pagesRepository;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Page> event) {
        if (event.getSource().getTitle() == null || event.getSource().getTitle().trim().isEmpty())
            throw new InvalidPageException(TITLE_EMPTY_EXCEPTION);
        String parentPageId = event.getSource().getParentPageId();
        if (parentPageId != null && !pagesRepository.findById(parentPageId).isPresent())
            throw new PageNotFound(String.format(PARENT_PAGE_ID_NOT_FOUND, parentPageId));
        event.getSource().setIsFavourite(event.getSource().getIsFavourite() != null ? event.getSource().getIsFavourite() : false);
        Long timestamp = new Date().getTime();
        if(event.getSource().getCreatedAt() == null)
            event.getSource().setCreatedAt(timestamp);
        event.getSource().setUpdatedAt(timestamp);
        super.onBeforeConvert(event);
    }

}
