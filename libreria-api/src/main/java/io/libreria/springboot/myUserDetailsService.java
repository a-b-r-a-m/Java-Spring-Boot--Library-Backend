package io.libreria.springboot;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class myUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
				//.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthorities(user));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String userRole = user.getRole().getName();
		
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRole);
		return authorities;
	}
}

