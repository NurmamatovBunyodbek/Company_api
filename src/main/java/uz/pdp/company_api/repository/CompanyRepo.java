package uz.pdp.company_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.company_api.entity.Company;

public interface CompanyRepo extends JpaRepository<Company,Integer> {


}
