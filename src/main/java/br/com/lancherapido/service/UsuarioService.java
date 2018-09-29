package br.com.lancherapido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lancherapido.model.Usuario;
import br.com.lancherapido.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
		
	public Boolean cadastraUsuario(Usuario usuario) {
		String name = usuario.getName();
		String cpf = usuario.getCpf();
		String phone = usuario.getPhone();
		String email = usuario.getEmail();
		String password = usuario.getPassword();
		
		if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !emailExistente(email)) {
				Usuario novoUsuario = new Usuario(name, cpf, phone, email, password);
				usuarioRepository.save(novoUsuario);
				return true;
		}
		return false;
	}
	
	public List<Usuario> getTodosUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> getUsuarioPorId(Long id) {
		return usuarioRepository.findById(id);
	}
	
	private Boolean emailExistente(String email) {
		List<Usuario> list = usuarioRepository.findByEmail(email);
		if(!list.isEmpty()) {
			return true;
		}
		return false;
	}
}
