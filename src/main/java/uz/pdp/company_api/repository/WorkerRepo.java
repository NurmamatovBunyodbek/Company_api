package uz.pdp.company_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.company_api.entity.Worker;

public interface WorkerRepo extends JpaRepository<Worker , Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

}
