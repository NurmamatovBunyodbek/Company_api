package uz.pdp.company_api.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    @NotNull(message = "The workerName cannot be empty")
    private String name;
    @NotNull(message = "The phoneNumber cannot be empty")
    private String phoneNumber;
    private Integer addressId;
    private Integer deportmentId;
}
