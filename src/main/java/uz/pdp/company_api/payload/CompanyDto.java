package uz.pdp.company_api.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "The corpName cannot be empty")
    private String corpName;
    @NotNull(message = "The directorName cannot be empty")
    private String directorName;
    private Integer addressId;
}
