package gabrielebelluco.u5d5w3.repositories;

import gabrielebelluco.u5d5w3.entities.Evento;
import gabrielebelluco.u5d5w3.entities.Prenotazioni;
import gabrielebelluco.u5d5w3.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazioni, UUID> {
    boolean existsByUtenteAndEvento(Utente utente, Evento evento);

    long countByEvento(Evento evento); // countBy serve epr contare quanti N di una determinata cosa con una query derivata in questo caso per i posti https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
}