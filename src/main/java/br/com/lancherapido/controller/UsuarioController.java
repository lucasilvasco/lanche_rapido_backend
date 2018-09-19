package br.com.lancherapido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lancherapido.model.Usuario;
import br.com.lancherapido.repository.UsuarioRepository;

@Controller
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;	
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Usuario> clientePorId(@PathVariable Long id) {
		Optional<Usuario> clienteId = usuarioRepository.findById(id);
		return clienteId;
	}
	
	
	
	@PostMapping("/insere")
	public ResponseEntity<Boolean> insere(@RequestParam("name") String name,
						@RequestParam("email") String email,
						@RequestParam("password") String password) {
		Boolean response = cadastraUsuario(name.trim(), email.trim(), password.trim());
		if(response) {
			return new ResponseEntity<Boolean>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	private Boolean cadastraUsuario(String name, String email, String password) {
		if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
			Usuario novoUsuario = new Usuario(name, email, password);
			usuarioRepository.save(novoUsuario);
			return true;
		}
		return false;
	}

}
