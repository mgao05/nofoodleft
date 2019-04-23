package com.molly.repository;

import com.molly.domain.Authority;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    List<Authority> findAll();
    List<Authority> findByUser_Id(Long id);
}
