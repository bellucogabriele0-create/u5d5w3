package gabrielebelluco.u5d5w3.services;

import gabrielebelluco.u5d5w3.entities.Evento;
import gabrielebelluco.u5d5w3.entities.Prenotazioni;
import gabrielebelluco.u5d5w3.entities.Utente;
import gabrielebelluco.u5d5w3.exception.BadRequestException;
import gabrielebelluco.u5d5w3.exception.NotFoundException;
import gabrielebelluco.u5d5w3.repositories.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class PrenotazioneService {
    private final PrenotazioniRepository prenotazioniRepository;
    private final EventoService eventoService;

    @Autowired
    public PrenotazioneService(PrenotazioniRepository prenotazioniRepository, EventoService eventoService) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.eventoService = eventoService;
    }

    public Prenotazioni save(Utente utente, UUID eventoId) {
        Evento evento = this.eventoService.findById(eventoId);
        if (this.prenotazioniRepository.existsByUtenteAndEvento(utente, evento)) {
            throw new BadRequestException("l'utente " + utente.getUsername() + " è già prenotato per l'evento: " + evento.getTitolo());
        }
        long postiOccupati = this.prenotazioniRepository.countByEvento(evento);
        if (postiOccupati >= evento.getPostiTotali()) {
            throw new BadRequestException("i posti per l'evento " + evento.getTitolo() + " sono fiinti");
        }
        Prenotazioni nuovaPrenotazione = new Prenotazioni(utente, evento);
        Prenotazioni saved = this.prenotazioniRepository.save(nuovaPrenotazione);

        log.info("prenotazione confermata con ID: " + saved.getId());
        return saved;
    }

    public void findByIdAndDelete(UUID id) {
        Prenotazioni found = this.prenotazioniRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        this.prenotazioniRepository.delete(found);
    }
}