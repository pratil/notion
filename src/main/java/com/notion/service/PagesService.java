package com.notion.service;

import com.notion.dto.page.request.UpsertPageDto;
import com.notion.dto.page.response.PageDto;
import com.notion.dto.page.response.PageNameDto;
import com.notion.exceptions.page.InvalidPageException;
import com.notion.model.Page;
import com.notion.repositry.PagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.notion.constants.Constants.PageException.PAGE_ID_NOT_FOUND;

@Service
public class PagesService {

    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    private BlockService blockService;

    public PageDto savePage(UpsertPageDto upsertPageDto) {
        return new PageDto(pagesRepository.save(upsertPageDto.getPage()));
    }

    public PageDto updatePage(String id, UpsertPageDto upsertPageDto) {
        Page page = getPageById(id);
        if(upsertPageDto.getTitle() != null)
            page.setTitle(upsertPageDto.getTitle());
        if(upsertPageDto.getIsFavourite() != null)
            page.setIsFavourite(upsertPageDto.getIsFavourite());
        if(upsertPageDto.getParentId() != null)
            page.setParentPageId(upsertPageDto.getParentId());
        return new PageDto(pagesRepository.save(page));
    }

    public PageDto getPage(String id) {
        return new PageDto(getPageById(id), getAllNestedPageNames(id), blockService.fetchAllBlocksByPageId(id));
    }

    public PageDto deletePage(String id) {
        Page page = getPageById(id);
        pagesRepository.delete(page);
        return new PageDto(page);
    }

    public List<PageDto> getAllPageDTOs(Boolean isFavourite) {
        List<Page> pages;
        if(isFavourite == null)
            pages = pagesRepository.findAllByParentPageId(null);
        else
            pages = pagesRepository.findAllByParentPageIdAndIsFavourite(null, isFavourite);
        List<PageDto> pageDtos = new ArrayList<>();
        pages.forEach(page -> {
            pageDtos.add(new PageDto(page, getAllNestedPageNames(page.getId()), blockService.fetchAllBlocksByPageId(page.getId())));
        });
        return pageDtos;
    }

    public List<PageNameDto> getAllPageNames(Boolean isFavourite) {
        List<Page> pages;
        if(isFavourite == null)
            pages = pagesRepository.findAll();
        else
            pages = pagesRepository.findAllByIsFavourite(isFavourite);
        return pages.stream().map(PageNameDto::new).collect(Collectors.toList());
    }

    private List<PageNameDto> getAllNestedPageNames(String pageId) {
        try {
            List<Page> pages = pagesRepository.findAllByParentPageId(pageId);
            return pages.stream().map(PageNameDto::new).collect(Collectors.toList());
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    private Page getPageById(String id) {
        Page page = pagesRepository.findById(id).orElse(null);
        if(page == null)
            throw new InvalidPageException(String.format(PAGE_ID_NOT_FOUND, id));
        return page;
    }

}
