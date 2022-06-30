package com.notion.repositry;

import com.notion.model.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PagesRepository extends MongoRepository<Page, String> {

    @Query(value = "{name : ?0, active: true}")
    Optional<Page> findByName(String title);

    @Query(value = "{parent_id : ?0, active: true}")
    List<Page> findAllByParentPageId(String id);

    @Query(value = "{parent_id : ?0, favourite: ?1, active: true}")
    List<Page> findAllByParentPageIdAndIsFavourite(String id, Boolean isFavourite);

    @Query(value = "{favourite: ?0, active: true}")
    List<Page> findAllByIsFavourite(Boolean isFavourite);

}
