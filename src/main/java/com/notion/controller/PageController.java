package com.notion.controller;

import com.notion.dto.BaseResponse;
import com.notion.dto.page.request.UpsertPageDto;
import com.notion.dto.page.response.PageDto;
import com.notion.dto.page.response.PageNameDto;
import com.notion.service.DatabaseService;
import com.notion.service.PagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.notion.constants.Constants.FORWARD_SLASH;
import static com.notion.constants.Constants.Path.ID;
import static com.notion.constants.Constants.Path.PAGE_ENDPOINT;

@RestController
@RequestMapping(PAGE_ENDPOINT)
public class PageController {

    @Autowired
    private PagesService pagesService;

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse<PageDto> savePage(@RequestBody UpsertPageDto upsertPageDto) {
        return BaseResponse.created(pagesService.savePage(upsertPageDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = ID)
    public BaseResponse<PageDto> savePage(@PathVariable String id, @RequestBody UpsertPageDto upsertPageDto) {
        return BaseResponse.ok(pagesService.updatePage(id, upsertPageDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = ID)
    public BaseResponse<PageDto> getPage(@PathVariable String id) {
        return BaseResponse.ok(pagesService.getPage(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = ID)
    public BaseResponse<PageDto> deletePage(@PathVariable String id) {
        return BaseResponse.ok(pagesService.deletePage(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public BaseResponse<List<PageDto>> getPages(@RequestParam(value = "favourite", required = false) Boolean isFavourite) {
        return BaseResponse.ok(pagesService.getAllPageDTOs(isFavourite));
    }

    @RequestMapping(method = RequestMethod.GET, value = FORWARD_SLASH + "names")
    public BaseResponse<List<PageNameDto>> getPageNames(@RequestParam(value = "favourite", required = false) Boolean isFavourite) {
        return BaseResponse.ok(pagesService.getAllPageNames(isFavourite));
    }

}
