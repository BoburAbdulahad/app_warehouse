package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.app_warehouse.entity.Client;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> get(){
        List<Client> all = clientRepository.findAll();
        return all;
    }

    public Client getById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Client();
        return optionalClient.get();
    }

    public Result add(Client comeClient){
        boolean existsByNameAndPhoneNumber = clientRepository.existsByPhoneNumber(comeClient.getPhoneNumber());
        if (existsByNameAndPhoneNumber)
            return new Result("This client already exist",false);
        Client client=new Client();
        client.setName(comeClient.getName());
        client.setPhoneNumber(comeClient.getPhoneNumber());
        clientRepository.save(client);
        return new Result("Client successfully added",true);
    }

    public Result edit(Integer id,Client client){
        boolean existsByPhoneNumberAndIdNot = clientRepository.existsByPhoneNumberAndIdNot(client.getPhoneNumber(), id);
        if (existsByPhoneNumberAndIdNot)
            return new Result("Phone number already exist",false);
        if (!clientRepository.findById(id).isPresent()) {
            return new Result("Client not found",false);
        }
        Client editingClient = clientRepository.getReferenceById(id);
        editingClient.setName(client.getName());
        editingClient.setPhoneNumber(client.getPhoneNumber());
        Client savedClient = clientRepository.save(editingClient);
        return new Result("Client edited",true,savedClient);
    }

    public Result delete(Integer id){
        try {
            clientRepository.deleteById(id);
            return new Result("Client deleted",true);
        }catch (Exception e){
            return new Result("Error in deleting",false);
        }
    }

}
