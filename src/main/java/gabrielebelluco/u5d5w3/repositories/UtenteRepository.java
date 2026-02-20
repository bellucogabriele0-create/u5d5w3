package gabrielebelluco.u5d5w3.repositories;

import gabrielebelluco.u5d5w3.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByUsername(String username);

    boolean existsByUsername(String username);
}
