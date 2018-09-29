package br.com.lancherapido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lancherapido.model.Usuario;
import br.com.lancherapido.service.AuthService;

@Controller
@RestController
@RequestMapping(path = "/auth", consumes = "application/json", produces = "application/json")
public class authController {

	@Autowired
	AuthService authService;
	
	@PostMapping
	@CrossOrigin(origins="*")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario) {	
		Usuario response = authService.authenticarUsuario(usuario.getEmail().trim(),
															usuario.getPassword().trim());
		if(!response.getEmail().equals("") || !response.getPassword().equals("")){
			return new ResponseEntity<Usuario>(response,HttpStatus.OK);
		}
		return new ResponseEntity<Usuario> (response, HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/forgetpassword")
	@CrossOrigin(origins="*")
	public ResponseEntity<Usuario> validarForgetEmail(@RequestBody Usuario usuario){
		Usuario response = authService.validarEmailExistente(usuario.getEmail().trim());
		if(!response.getEmail().equals("")){
			return new ResponseEntity<Usuario>(response,HttpStatus.OK);
		}
		return new ResponseEntity<Usuario> (response, HttpStatus.UNAUTHORIZED);
	}
	
	@PutMapping("/newpassword")
	@CrossOrigin(origins="*")
	public ResponseEntity<Usuario> atualizarSenha(@RequestBody Usuario usuario){
		Usuario response = authService.atualizarSenha(usuario.getEmail().trim(), usuario.getPassword().trim());
		if(!response.getEmail().equals("")){
			return new ResponseEntity<Usuario>(response,HttpStatus.OK);
		}
		return new ResponseEntity<Usuario> (response, HttpStatus.UNAUTHORIZED);
	}
}
