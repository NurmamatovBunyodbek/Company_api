package uz.pdp.company_api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.company_api.entity.Address;
import uz.pdp.company_api.payload.AddressDto;
import uz.pdp.company_api.payload.Result;
import uz.pdp.company_api.repository.AddressRepo;

import java.util.List;
import java.util.Optional;

@Service

public class AddressService {

    @Autowired
    AddressRepo addressRepo;

    public List<Address> addressList() {
        List<Address> all = addressRepo.findAll();
        return all;
    }

    public Address addressId(Integer id) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        return optionalAddress.get();
    }

    public Result addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepo.save(address);
        return new Result("Added Address", true);
    }

    public Result editingAddress(Integer id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setStreet(addressDto.getStreet());
            address.setHomeNumber(addressDto.getHomeNumber());
            addressRepo.save(address);
            return new Result("Editing address", true);
        }
        return new Result("Address not found", false);
    }

    public Result deletedAddress(Integer id) {
        addressRepo.deleteById(id);
        return new Result("Address deleted", true);
    }

}
