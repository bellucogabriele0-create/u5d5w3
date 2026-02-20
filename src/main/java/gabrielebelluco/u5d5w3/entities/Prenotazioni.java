package gabrielebelluco.u5d5w3.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
public class Prenotazioni {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Evento evento;

    public Prenotazioni(Utente utente, Evento evento) {
        this.utente = utente;
        this.evento = evento;
    }
}
