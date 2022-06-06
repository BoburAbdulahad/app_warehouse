package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Client;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public List<Client> get() {
        return clientService.get();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id){
        return clientService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody Client client) {
        return clientService.add(client);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Client client) {
        return clientService.edit(id, client);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return clientService.delete(id);
    }
}
