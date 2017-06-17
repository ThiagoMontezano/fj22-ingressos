package br.com.caelum.ingresso.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

	/**
	 * @deprecated hibernate only
	 */
	public Usuario(){
		
	}
	
	public Usuario(Integer id, String email, String password, Set<Permissao> permissoes) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.permissoes = permissoes;
	}

	public Usuario(String email, String password, Set<Permissao> permissoes) {
		this.email = email;
		this.password = password;
		this.permissoes = permissoes;
	}

	@Id
	@GeneratedValue
	private Integer id;
	
	private String email;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	private String password;
	
	@ManyToMany
	private Set<Permissao> permissoes = new HashSet<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissoes;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
