package ViniciusSoares96.com.github.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ViniciusSoares96.com.github.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer > {
	
}
