package com.notion.service;

import com.notion.dto.BaseResponse;
import com.notion.dto.block.request.UpsertBlockDto;
import com.notion.dto.block.response.BlockDto;
import com.notion.exceptions.block.BlockNotFound;
import com.notion.exceptions.block.InvalidBlockException;
import com.notion.model.block.Block;
import com.notion.repositry.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.notion.constants.Constants.BlockException.IDS_MISMATCH_EXCEPTION;

@Service
public class BlockService {

    private final BlockRepository blockRepository;

    @Autowired
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public BaseResponse<List<BlockDto>> upsertBlocks(List<UpsertBlockDto> upsertBlocks) {
        List<BlockDto> blocks = new ArrayList<>();
        Map<String, List<UpsertBlockDto>> errors = new HashMap<>();
        upsertBlocks.forEach(upsertBlockDto -> {
            try{
                blocks.add(upsertBlock(upsertBlockDto));
            } catch (Exception exception) {
                List<UpsertBlockDto> list = errors.getOrDefault(exception.getMessage(), new ArrayList<>());
                list.add(upsertBlockDto);
                errors.put(exception.getMessage(), list);
            }
        });
        return BaseResponse.created(blocks, errors);
    }

    public BaseResponse<List<BlockDto>> deleteBlocks(List<String> ids) {
        List<BlockDto> blocks = new ArrayList<>();
        Map<String, List<String>> errors = new HashMap<>();
        ids.forEach(id -> {
            try {
                blocks.add(deleteBlock(id));
            } catch (Exception exception) {
                List<String> list = errors.getOrDefault(exception.getMessage(), new ArrayList<>());
                list.add(id);
                errors.put(exception.getMessage(), list);
            }
        });
        return BaseResponse.ok(blocks, errors);
    }

    public BlockDto deleteBlock(String id) {
        Block block = getBlockById(id);
        blockRepository.delete(block);
        return new BlockDto(block);
    }

    public BlockDto getBlock(String id) {
        return new BlockDto(getBlockById(id));
    }

    public BlockDto upsertBlock(UpsertBlockDto upsertBlockDto) {
        if(upsertBlockDto.getId() == null)
            return new BlockDto(blockRepository.save(upsertBlockDto.getBlock()));
        return updateBlock(upsertBlockDto);
    }

    public BlockDto updateBlock(UpsertBlockDto upsertBlockDto) {
        return updateBlock(upsertBlockDto.getId(), upsertBlockDto);
    }

    public BlockDto updateBlock(String id, UpsertBlockDto upsertBlockDto) {
        if(upsertBlockDto.getId() != null && !upsertBlockDto.getId().equals(id))
            throw new InvalidBlockException(String.format(IDS_MISMATCH_EXCEPTION, id, upsertBlockDto.getId()));
        Block block = getBlockById(id);
        Block updatedBlock = upsertBlockDto.getBlock();
        if(updatedBlock.getOrder() == null)
            updatedBlock.setOrder(block.getOrder());
        if(block.equals(updatedBlock))
            return new BlockDto(block);
        if(updatedBlock.getValue() != null)
            block.setValue(updatedBlock.getValue());
        if(updatedBlock.getType() != null)
            block.setType(updatedBlock.getType());
        if(updatedBlock.getSubType() != null)
            block.setSubType(updatedBlock.getSubType());
        if(updatedBlock.getPageId() != null)
            block.setPageId(updatedBlock.getPageId());

        return new BlockDto(blockRepository.save(block));
    }

    public List<BlockDto> fetchAllBlocksByPageId(String pageId) {
        try {
            List<BlockDto> blocks = new ArrayList<>();
            blockRepository.findAllByPageIdOrderByOrder(pageId).forEach(block -> blocks.add(new BlockDto(block)));
            return blocks;
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    private Block getBlockById(String id) {
        Block block = blockRepository.findById(id).orElse(null);
        if (block == null)
            throw new BlockNotFound();
        return block;
    }


}
