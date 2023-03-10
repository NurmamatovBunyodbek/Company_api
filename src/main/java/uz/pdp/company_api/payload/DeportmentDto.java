package uz.pdp.company_api.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeportmentDto {

    @NotNull(message = "The Deportment Name cannot be empty")
    private String name;
    private Integer companyId;

}
