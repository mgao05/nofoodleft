package com.molly.repository;

import com.molly.domain.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findAll();
    List<Image> findByUser_Id(Long id);

}
