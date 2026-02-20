package gabrielebelluco.u5d5w3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "evento")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Evento {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String location;
    private int postiTotali;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Utente organizer;

    public Evento(String titolo, String descrizione, LocalDate data, String location, int postiTotali, Utente organizer) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.location = location;
        this.postiTotali = postiTotali;
        this.organizer = organizer;
    }
}
