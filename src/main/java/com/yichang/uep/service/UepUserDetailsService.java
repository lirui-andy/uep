package com.yichang.uep.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.yichang.uep.dto.UepUser;
import com.yichang.uep.model.YUser;
import com.yichang.uep.model.YUserPerm;
import com.yichang.uep.repo.UserPermRepo;
import com.yichang.uep.repo.UserRepo;

@Component
public class UepUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	UserPermRepo userPermRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<YUser> user = userRepo.findTop1ByUserName(username);
		if(user.isPresent()){
			UepUser uu = new UepUser();
			uu.setOrgId(user.get().getOrgId());
			uu.setOrgName(user.get().getOrgName());
			uu.setPassword(user.get().getPwd());
			uu.setRealName(user.get().getRealName());
			uu.setUserId(user.get().getUserId());
			uu.setUsername(user.get().getUserName());
			uu.setAuthorities(loadUserAuthorities(username));
			return uu;
		}
		else{
			throw new UsernameNotFoundException("用户名不存在:"+username);
		}
	}

	private Collection<? extends GrantedAuthority> loadUserAuthorities(String username) {
		List<YUserPerm>  perms = userPermRepo.findByUserName(username);
		List<GrantedAuthority> list = new LinkedList<>();
		perms.forEach( p -> {
			String pcode = p.getPermcode();
			if(!pcode.startsWith("ROLE_"))
				pcode = "ROLE_"+pcode;
			list.add(new SimpleGrantedAuthority(pcode));
		});
		list.add(new SimpleGrantedAuthority("ROLE_USER"));
		return list;
	}

}
