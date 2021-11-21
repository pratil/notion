package com.notion.controller;

import com.notion.dto.BaseResponse;
import com.notion.dto.page.request.UpsertPageDto;
import com.notion.dto.page.response.PageDto;
import com.notion.dto.page.response.PageNameDto;
import com.notion.service.DatabaseService;
import com.notion.service.PagesService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PageController {

    @Autowired
    private PagesService pagesService;

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(method = RequestMethod.POST, value = "/page")
    public BaseResponse<PageDto> savePage(@RequestBody UpsertPageDto upsertPageDto) {
        return BaseResponse.created(pagesService.savePage(upsertPageDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/page/{id}")
    public BaseResponse<PageDto> savePage(@PathVariable("id") String id, @RequestBody UpsertPageDto upsertPageDto) {
        return BaseResponse.ok(pagesService.updatePage(id, upsertPageDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/page/{id}")
    public BaseResponse<PageDto> getPage(@PathVariable("id") String id) {
        return BaseResponse.ok(pagesService.getPage(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/page/{id}")
    public BaseResponse<PageDto> deletePage(@PathVariable("id") String id) {
        return BaseResponse.ok(pagesService.deletePage(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pages")
    public BaseResponse<List<PageDto>> getPages(@RequestParam(value = "favourite", required = false) Boolean isFavourite) {
        return BaseResponse.ok(pagesService.getAllPageDTOs(isFavourite));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pages/names")
    public BaseResponse<List<PageNameDto>> getPageNames(@RequestParam(value = "favourite", required = false) Boolean isFavourite) {
        return BaseResponse.ok(pagesService.getAllPageNames(isFavourite));
    }

}
