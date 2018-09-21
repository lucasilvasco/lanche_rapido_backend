package br.com.lancherapido.controller;

import java.util.List;

import javax.xml.ws.http.HTTPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.lancherapido.model.Usuario;
import br.com.lancherapido.repository.UsuarioRepository;
import br.com.lancherapido.service.UsuarioService;;

@Controller
@RequestMapping(path = "/auth", consumes = "application/json", produces = "application/json")
public class authController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	@CrossOrigin(origins="*")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario) {	
		Usuario response = usuarioService.authenticarUsuario(usuario.getEmail().trim(),
															usuario.getPassword().trim());
		if(!response.getEmail().equals("")) {
			return new ResponseEntity<Usuario>(response,HttpStatus.OK);
		}
		return new ResponseEntity<Usuario> (response, HttpStatus.UNAUTHORIZED);
	}
}
