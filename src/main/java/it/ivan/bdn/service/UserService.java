package it.ivan.bdn.service;

import java.util.Collection;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.ivan.bdn.model.User;
import it.ivan.bdn.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService 
{
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
	    User user = userRepository.findByUsername(username);
	    if (user == null) {
	    	throw new UsernameNotFoundException("User not found: " + username);
	    }
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(),true, true, true, true,
				getAuthorities(user));
	}
	
	private Collection<GrantedAuthority> getAuthorities(User user) {
        return AuthorityUtils.createAuthorityList(user.getRole());
	}
} 
