package uz.pdp.company_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.company_api.entity.Address;

public interface AddressRepo extends JpaRepository<Address , Integer> {


}
