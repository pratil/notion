package com.notion.events;

import com.notion.model.block.Block;
import com.notion.repositry.BlockRepository;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BlockListener extends AbstractMongoEventListener<Block> {

    private final BlockRepository blockRepository;

    public BlockListener(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Block> event) {
        if(event.getSource().getOrder() == null) {
            List<Block> blocks = blockRepository.findLastOrderByPageId(event.getSource().getPageId());
            long order = 1L;
            if(!blocks.isEmpty())
                order = blocks.get(0).getOrder()+1;
            event.getSource().setOrder(order);
        }
        Long timestamp = new Date().getTime();
        if(event.getSource().getCreatedAt() == null)
            event.getSource().setCreatedAt(timestamp);
        event.getSource().setUpdatedAt(timestamp);
        super.onBeforeConvert(event);
    }

}
