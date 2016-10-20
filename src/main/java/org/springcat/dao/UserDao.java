package org.springcat.dao;

import org.springcat.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDao extends PagingAndSortingRepository<User, Long> {

   Page<User> findByLoginName(String loginName, Pageable pageable);

   Page<User> findAll(Pageable pageable);


}