package ViniciusSoares96.com.github.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ViniciusSoares96.com.github.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer > {
	
}
