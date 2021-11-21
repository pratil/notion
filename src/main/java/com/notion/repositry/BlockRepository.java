package com.notion.repositry;

import com.notion.model.block.Block;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BlockRepository extends MongoRepository<Block, String> {
    Iterable<Block> findAllByPageIdOrderByOrder(String pageId);

    @Query(value = "{page_id: ?0}", sort = "{order: -1}")
    List<Block> findLastOrderByPageId(String pageId);
}
