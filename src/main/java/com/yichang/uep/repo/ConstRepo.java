package com.yichang.uep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YConst;

public interface ConstRepo extends JpaRepository<YConst, Integer> {

	List<YConst> findByConstGroup(String constGroup);
}
