package uz.pdp.company_api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.company_api.entity.Address;
import uz.pdp.company_api.entity.Company;
import uz.pdp.company_api.payload.CompanyDto;
import uz.pdp.company_api.payload.Result;
import uz.pdp.company_api.repository.AddressRepo;
import uz.pdp.company_api.repository.CompanyRepo;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    AddressRepo addressRepo;

    public List<Company> companies() {
        List<Company> list = companyRepo.findAll();
        return list;
    }

    public Company getCompanyId(Integer id) {
        Optional<Company> optionalCompany = companyRepo.findById(id);
        return optionalCompany.get();
    }

    public Result addCompany(CompanyDto companyDto) {

        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Optional<Address> optionalAddress = addressRepo.findById(companyDto.getAddressId());
        if (optionalAddress.isPresent()) {
            return new Result("company not found", true);
        }
        company.setAddress(optionalAddress.get());
        companyRepo.save(company);
        return new Result("Company added", true);
    }

    public Result editCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> companyOptional = companyRepo.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setCorpName(companyDto.getCorpName());
            company.setDirectorName(companyDto.getDirectorName());
            Optional<Address> addressOptional = addressRepo.findById(companyDto.getAddressId());
            company.setAddress(addressOptional.get());
            companyRepo.save(company);
            return new Result("Company editing", true);
        }
        return new Result("Company not found", false);
    }

    public Result deletedCompany(Integer id) {
        companyRepo.deleteById(id);
        return new Result("Company deleted", true);
    }

}
