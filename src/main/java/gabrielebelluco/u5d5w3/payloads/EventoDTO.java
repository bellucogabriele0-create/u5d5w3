package gabrielebelluco.u5d5w3.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EventoDTO(
        @NotBlank(message = "titolos obbligatorio")
        @Size(min = 3, max = 100, message = "il titolo deve essere dai 3 ai 100 caratteri")
        String titolo,
        @NotBlank(message = "descrizione obbligatoria")
        String descrizione,
        @NotNull(message = "data obbligatoria")
        @Future(message = "la data dell'evento dnon può essere già passata")
        LocalDate data,
        @NotBlank(message = "location obbligatoria")
        String location,
        @Min(value = 1, message = "l'evento deve avere almeno un posto disponibile")
        int postiTotali) {
}
