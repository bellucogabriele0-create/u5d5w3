package gabrielebelluco.u5d5w3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "utente")
public class Utente {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue
    private UUID utenteId;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public Utente(String username, String password, Ruolo ruolo) {
        this.username = username;
        this.password = password;
        this.ruolo = Ruolo.UTENTE_NORMALE;
    }
}
