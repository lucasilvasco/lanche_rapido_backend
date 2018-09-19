package br.com.lancherapido.controller;

import java.util.List;

import javax.xml.ws.http.HTTPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.lancherapido.model.Usuario;
import br.com.lancherapido.repository.UsuarioRepository;;

@Controller
@RequestMapping("/auth")
public class authController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	@CrossOrigin(origins="*")
	public ResponseEntity<Boolean> logar(@RequestParam("name") String name,
							@RequestParam("password") String password) {
	
		Boolean response = authenticar(name.trim(), password.trim());

		return new ResponseEntity<Boolean> (response, HttpStatus.OK);
		
	}
	
	private Boolean authenticar(String name, String password) {
		List<Usuario> list = usuarioRepository.findByNameAndPassword(name, password);
		if(!list.isEmpty()& list.size()==1) {
			return true;
		}
		return false;
	}

}
