package gabrielebelluco.u5d5w3.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UtenteDTO(
        @NotBlank(message = "username obbligatorio")
        @Size(min = 5, max = 20, message = "lo username deve essere dai 4 ai 20 caratteri")
        String username,
        @NotBlank(message = "la password è obbligatoria")
        @Size(min = 5, message = "la password deve avere almeno 5 caratteri")
        String password,
        @NotBlank(message = "il ruolo è obbligatorio (ORGANIZZATORE o UTENTE_NORMALE)")
        String ruolo
) {
}
