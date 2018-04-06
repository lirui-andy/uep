package com.yichang.uep.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YUser;

public interface UserRepo extends JpaRepository<YUser, Integer> {

	Optional<YUser> findTop1ByUserName(String username);


}
