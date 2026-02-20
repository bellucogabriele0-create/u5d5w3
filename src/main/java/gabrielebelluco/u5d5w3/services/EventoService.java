package gabrielebelluco.u5d5w3.services;

import gabrielebelluco.u5d5w3.entities.Evento;
import gabrielebelluco.u5d5w3.entities.Utente;
import gabrielebelluco.u5d5w3.exception.NotFoundException;
import gabrielebelluco.u5d5w3.payloads.EventoDTO;
import gabrielebelluco.u5d5w3.repositories.EventoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EventoService {
    private final EventoRepository eventoRepo;

    @Autowired
    public EventoService(EventoRepository eventoRepo) {
        this.eventoRepo = eventoRepo;
    }


    public Evento save(EventoDTO body, Utente organizer) {
        Evento newEvento = new Evento(body.titolo(), body.descrizione(), body.data(), body.location(), body.postiTotali(), organizer);
        Evento saved = this.eventoRepo.save(newEvento);
        log.info("hai salvato l'evento con id " + saved.getId());
        return saved;
    }

    public Evento findById(UUID id) {
        return this.eventoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Evento findByIdAndUpdate(UUID id, EventoDTO body) {
        Evento found = this.findById(id);
        found.setTitolo(body.titolo());
        found.setDescrizione(body.descrizione());
        found.setData(body.data());
        found.setLocation(body.location());
        found.setPostiTotali(body.postiTotali());
        return this.eventoRepo.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Evento found = this.findById(id);
        this.eventoRepo.delete(found);
    }
}