package com.notion.controller;

import com.notion.dto.BaseResponse;
import com.notion.dto.block.request.UpsertBlockDto;
import com.notion.dto.block.response.BlockDto;
import com.notion.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlockController {

    @Autowired
    private BlockService blockService;

    @RequestMapping(method = RequestMethod.POST, value = "/block")
    public BaseResponse<List<BlockDto>> insertBlock(@RequestBody List<UpsertBlockDto> upsertBlockDto) {
        return blockService.upsertBlocks(upsertBlockDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/block/{id}")
    public BaseResponse<BlockDto> updateBlock(@PathVariable String id, @RequestBody UpsertBlockDto upsertBlockDto) {
        return BaseResponse.ok(blockService.updateBlock(id, upsertBlockDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/block/{id}")
    public BaseResponse<BlockDto> getBlock(@PathVariable String id) {
        return BaseResponse.ok(blockService.getBlock(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/block/{id}")
    public BaseResponse<BlockDto> deleteBlock(@PathVariable String id) {
        return BaseResponse.ok(blockService.deleteBlock(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/block")
    public BaseResponse<List<BlockDto>> deleteBlock(@RequestBody List<String> ids) {
        return blockService.deleteBlocks(ids);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blocks")
    public BaseResponse<List<BlockDto>> fetchAllBlocks(@RequestParam("page_id") String pageId) {
        return BaseResponse.ok(blockService.fetchAllBlocksByPageId(pageId));
    }

}
