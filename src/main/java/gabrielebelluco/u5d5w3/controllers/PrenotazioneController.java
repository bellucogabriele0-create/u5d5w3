package gabrielebelluco.u5d5w3.controllers;

import gabrielebelluco.u5d5w3.entities.Prenotazioni;
import gabrielebelluco.u5d5w3.entities.Utente;
import gabrielebelluco.u5d5w3.services.PrenotazioneService;
import gabrielebelluco.u5d5w3.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/{utenteId}/{eventoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazioni book(@PathVariable UUID utenteId, @PathVariable UUID eventoId) {
        Utente utente = utenteService.findById(utenteId);
        return prenotazioneService.save(utente, eventoId);
    }
}