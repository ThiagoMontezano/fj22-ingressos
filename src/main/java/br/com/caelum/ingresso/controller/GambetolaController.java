package br.com.caelum.ingresso.controller;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.ingresso.model.Permissao;
import br.com.caelum.ingresso.model.Usuario;

@RestController
public class GambetolaController {
	
	@PersistenceContext
	private EntityManager manager;
		
	@GetMapping("/gambetola")
	@Transactional
	public String gerarUsuario(){
		Permissao permissao = new Permissao("ROLE_ADMIN");
		manager.persist(permissao);
		
		Set<Permissao> permissoes = new HashSet<>();
		permissoes.add(permissao);
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		Usuario usuario = new Usuario("teste@teste.com.br",bCryptPasswordEncoder.encode("teste"), permissoes);
		
		manager.persist(usuario);
		return "Aeeeee gerou usuario na gambetola!!!";			
	}

}
