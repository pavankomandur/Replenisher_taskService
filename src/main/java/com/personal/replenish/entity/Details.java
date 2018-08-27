//package com.personal.replenish.entity;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class Details extends User implements UserDetails {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	public Details(final User user) {
//		super(user);
//	}
//	
//	/**
//	   * This method will retrieve the role of logged in user. This will be useful to 
//	   * get the role of logged in user to determine which function is accessible to him in controller.
//	   * 
//	   */
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<Role> roles = new HashSet<>();
//		roles.add(getRole());
//		List<SimpleGrantedAuthority> rolesList=roles
//				.stream()
//				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
//				.collect(Collectors.toList());
//		System.out.println("Roles are " + rolesList.get(0).getAuthority());
//		System.out.println("Roles are " + rolesList.toString());
//		return  rolesList;
//	}
//
//	@Override
//	public String getUsername() {
//		return super.getName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public String getPassword() {
//		return super.getPassword();
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//	 
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//}
