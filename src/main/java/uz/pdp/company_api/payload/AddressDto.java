package uz.pdp.company_api.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
        @NotNull(message = "The street cannot be empty")
        private String street;
        @NotNull(message = "The homeNumber cannot be empty")
        private String homeNumber;
}
