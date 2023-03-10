package uz.pdp.company_api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.company_api.entity.Company;
import uz.pdp.company_api.entity.Deportment;
import uz.pdp.company_api.payload.DeportmentDto;
import uz.pdp.company_api.payload.Result;
import uz.pdp.company_api.repository.CompanyRepo;
import uz.pdp.company_api.repository.DeportmentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DeportmentService {

    @Autowired
    DeportmentRepo deportmentRepo;
    @Autowired
    CompanyRepo companyRepo;

    public List<Deportment> deportmentList() {
        List<Deportment> deportmentsList = deportmentRepo.findAll();
        return deportmentsList;
    }

    public Deportment getDeportmentId(Integer id) {
        Optional<Deportment> optionalDeportment =
                deportmentRepo.findById(id);
        return optionalDeportment.get();
    }

    public Result addDeportment(DeportmentDto deportmentDto) {
        Deportment deportment = new Deportment();
        deportment.setName(deportmentDto.getName());
        Optional<Company> optionalCompany = companyRepo.findById(deportmentDto.getCompanyId());
        deportment.setCompany(optionalCompany.get());
        deportmentRepo.save(deportment);
        return new Result("Added", true);
    }

    public Result editDeportment(Integer id, DeportmentDto deportmentDto) {
        Optional<Deportment> byId = deportmentRepo.findById(id);
        if (byId.isPresent()) {
            Deportment deportment = byId.get();
            deportment.setName(deportmentDto.getName());
            Optional<Company> optionalCompany = companyRepo.findById(deportmentDto.getCompanyId());
            deportment.setCompany(optionalCompany.get());
            deportmentRepo.save(deportment);
            return new Result("Deportment editing", true);
        }
        return new Result("Deportment not found", false);
    }

    public Result deletedId(Integer id) {
        deportmentRepo.deleteById(id);
        return new Result("Deportment deleted", true);
    }

}
