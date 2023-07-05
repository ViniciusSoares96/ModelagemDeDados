package ViniciusSoares96.com.github.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ViniciusSoares96.com.github.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer > {
	
}
