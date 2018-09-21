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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lancherapido.model.Usuario;
import br.com.lancherapido.repository.UsuarioRepository;
import br.com.lancherapido.service.UsuarioService;

@Controller
@RestController
@RequestMapping(path = "/usuarios", consumes = "application/json", produces = "application/json")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;	
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioService.getTodosUsuarios();
	}
	
	@GetMapping("/{id}")
	public Optional<Usuario> clientePorId(@PathVariable Long id) {
		return usuarioService.getUsuarioPorId(id);
	}
	
	
	
	@PostMapping("/insere")
	public ResponseEntity<Boolean> insere(@RequestBody Usuario usuario) {
		Boolean response = usuarioService.cadastraUsuario(usuario);
		
		if(response) {
			return new ResponseEntity<Boolean>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
