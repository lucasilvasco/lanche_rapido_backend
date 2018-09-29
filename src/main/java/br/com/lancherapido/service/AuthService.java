package br.com.lancherapido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lancherapido.model.Usuario;
import br.com.lancherapido.repository.UsuarioRepository;

@Service
public class AuthService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario authenticarUsuario(String email, String password) {
		List<Usuario> list = usuarioRepository.findByEmailAndPassword(email, password);
		if(!list.isEmpty()& list.size()==1) {
			return list.get(0);
		}
		return new Usuario("","","","","");
	}
	
	public Usuario validarEmailExistente(String email) {
		List<Usuario> list = usuarioRepository.findByEmail(email);
		if(!list.isEmpty()& list.size()==1) {
			return list.get(0);
		}
		return new Usuario("","","","","");
	}
	
	public Usuario atualizarSenha(String email, String password) {
		List<Usuario> list = usuarioRepository.findByEmail(email);
		if(!list.isEmpty()& list.size()==1) {
			list.get(0).setPassword(password);
			usuarioRepository.save(list.get(0));
			return list.get(0);
		}
		return new Usuario();

	}
}
