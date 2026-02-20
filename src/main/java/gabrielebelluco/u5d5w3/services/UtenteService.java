package gabrielebelluco.u5d5w3.services;


import gabrielebelluco.u5d5w3.entities.Ruolo;
import gabrielebelluco.u5d5w3.entities.Utente;
import gabrielebelluco.u5d5w3.exception.BadRequestException;
import gabrielebelluco.u5d5w3.exception.NotFoundException;
import gabrielebelluco.u5d5w3.exception.UnauthorizedException;
import gabrielebelluco.u5d5w3.payloads.LoginDTO;
import gabrielebelluco.u5d5w3.payloads.UtenteDTO;
import gabrielebelluco.u5d5w3.repositories.UtenteRepository;
import gabrielebelluco.u5d5w3.security.JWTTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;


    @Autowired
    public UtenteService(UtenteRepository utenteRepository, JWTTools jwtTools, PasswordEncoder bcrypt) {
        this.utenteRepository = utenteRepository;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
    }

    public Utente save(UtenteDTO body) {
        this.utenteRepository.findByUsername(body.username()).ifPresent(user -> {
            throw new BadRequestException("questo username " + body.username() + " è già in uso");
        });
        Utente newUtente = new Utente(body.username(), bcrypt.encode(body.password()), Ruolo.valueOf(body.ruolo()));
        Utente saved = this.utenteRepository.save(newUtente);
        log.info("utente salvato con id : " + saved.getUtenteId());
        return saved;
    }

    public String authenticateUtenteAndGenerateToken(LoginDTO body) {
        Utente user = this.findByUsername(body.username());

        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.generateToken(user);
        } else {
            throw new UnauthorizedException("credenziali errate");
        }
    }

    public Utente findById(UUID id) {
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Utente findByUsername(String username) {
        return this.utenteRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(username + " : questo utente non è stato trovato"));
    }
}