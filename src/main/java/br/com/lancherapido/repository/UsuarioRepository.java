package br.com.lancherapido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lancherapido.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
		public List<Usuario> findByNameAndPassword(String name, String password);
}
