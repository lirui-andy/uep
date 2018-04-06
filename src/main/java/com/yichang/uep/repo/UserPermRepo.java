package com.yichang.uep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YUserPerm;

public interface UserPermRepo extends JpaRepository<YUserPerm, Integer> {

	List<YUserPerm> findByUserName(String username);


}
