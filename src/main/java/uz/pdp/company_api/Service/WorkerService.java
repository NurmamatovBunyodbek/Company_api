package uz.pdp.company_api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.company_api.entity.Address;
import uz.pdp.company_api.entity.Deportment;
import uz.pdp.company_api.entity.Worker;
import uz.pdp.company_api.payload.Result;
import uz.pdp.company_api.payload.WorkerDto;
import uz.pdp.company_api.repository.AddressRepo;
import uz.pdp.company_api.repository.DeportmentRepo;
import uz.pdp.company_api.repository.WorkerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepo workerRepo;

    @Autowired
    AddressRepo addressRepo;
    @Autowired
    DeportmentRepo deportmentRepo;


    public List<Worker> workers() {
        List<Worker> workerList = workerRepo.findAll();
        return workerList;
    }

    public Worker getWorkerID(Integer id) {
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        return optionalWorker.get();
    }

    public Result addWorker(WorkerDto workerDto) {

        boolean phoneNumber = workerRepo.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (phoneNumber){
            return new Result("Bunday worker mavjud",false);
        }

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepo.findById(workerDto.getAddressId());
        worker.setAddress(optionalAddress.get());
        Optional<Deportment> optionalDeportment = deportmentRepo.findById(workerDto.getDeportmentId());
        worker.setDeportment(optionalDeportment.get());
        workerRepo.save(worker);
        return new Result("Worker added", true);
    }

    public Result editWorker(Integer id, WorkerDto workerDto) {
        Optional<Worker> workerOptional = workerRepo.findById(id);
        if (workerOptional.isPresent()) {
            Worker worker = workerOptional.get();
            worker.setName(workerDto.getName());
            worker.setPhoneNumber(workerDto.getPhoneNumber());
            Optional<Address> optionalAddress = addressRepo.findById(workerDto.getAddressId());
            worker.setAddress(optionalAddress.get());
            Optional<Deportment> optionalDeportment = deportmentRepo.findById(workerDto.getDeportmentId());
            worker.setDeportment(optionalDeportment.get());
            workerRepo.save(worker);
            return new Result("Editing worker", true);
        }
        return new Result("Worker not found", false);
    }

    public  Result deletedWorker(Integer id ){
        workerRepo.deleteById(id);
        return new Result("Deleted ",true);
    }
}
