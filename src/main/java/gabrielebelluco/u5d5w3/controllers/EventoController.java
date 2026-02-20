package gabrielebelluco.u5d5w3.controllers;

import gabrielebelluco.u5d5w3.entities.Evento;
import gabrielebelluco.u5d5w3.entities.Utente;
import gabrielebelluco.u5d5w3.exception.ValidationException;
import gabrielebelluco.u5d5w3.payloads.EventoDTO;
import gabrielebelluco.u5d5w3.services.EventoService;
import gabrielebelluco.u5d5w3.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/{organizzatoreId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento create(@RequestBody @Validated EventoDTO body,
                         BindingResult validationResult,
                         @PathVariable UUID organizzatoreId) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream().map(e -> e.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);
        }
        Utente organizer = utenteService.findById(organizzatoreId);
        return eventoService.save(body, organizer);
    }

    @PutMapping("/{eventoId}")
    public Evento updateEvento(@PathVariable UUID eventoId, @RequestBody @Validated EventoDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
        }
        return eventoService.findByIdAndUpdate(eventoId, body);
    }

    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvento(@PathVariable UUID eventoId) {
        eventoService.findByIdAndDelete(eventoId);
    }
}