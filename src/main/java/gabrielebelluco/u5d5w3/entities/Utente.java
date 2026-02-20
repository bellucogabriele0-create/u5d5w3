package gabrielebelluco.u5d5w3.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "utente")
@JsonIgnoreProperties({"password", "accountNonExpired", "accountNonLocked", "authorities", "credentialsNonExpired", "enabled"})
public class Utente implements UserDetails {
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
        this.ruolo = ruolo;// qua in teoria potrei mettere di default un ruolo con Ruolo.ORGANIZZATORE tipo, ma preferisco senza
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
//003706ec-05ca-424c-bb82-a48444397b79 UTENTE
//f4de8b25-2f41-4949-823e-c8103e8eeb42 ORGANIZZATORE