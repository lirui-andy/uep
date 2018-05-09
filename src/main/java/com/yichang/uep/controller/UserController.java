package com.yichang.uep.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yichang.uep.dto.CommonOperResult;
import com.yichang.uep.model.YUser;
import com.yichang.uep.repo.UserRepo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/changepwd")
	public String view(){
		return "changepwd";
	}
	
	@PostMapping("/changepwd")
	@ResponseBody
	public CommonOperResult<?> change(@Param("oldPwd") String oldPwd,
			@Param("newPwd") String newPwd){
		Optional<YUser> user = userRepo.findTop1ByUserName(currentUser().getUserName());
		if(!user.isPresent() || !user.get().getPwd().equals(oldPwd)){
			return CommonOperResult.fail("-1", "旧密码错误");
		}
		YUser u = user.get();
		u.setPwd(newPwd);
		userRepo.save(u);
		return CommonOperResult.success();
	}
	
	@GetMapping("/success")
	public String success(){
		return "success";
	}
	
}
