package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.app_warehouse.entity.User;
import uz.bob.app_warehouse.entity.Warehouse;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.payload.UniversalClass;
import uz.bob.app_warehouse.payload.UserDto;
import uz.bob.app_warehouse.repository.UserRepository;
import uz.bob.app_warehouse.repository.WarehouseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    Set<Warehouse> warehouseSet=new HashSet<>();
    public List<User> getAll(){
       return userRepository.findAll();
    }

    public User getOneById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);
    }

    public Result add(UserDto userDto){
        warehouseSet.clear();
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("User already exist",false);
        User user=new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setCode(UniversalClass.forRandomCode());

        List<Integer> warehousesIds = userDto.getWarehousesIds();
        warehousesIds.forEach(integer ->warehouseRepository.findById(integer).ifPresent(warehouseSet::add));
        user.setWarehouses(warehouseSet);
        userRepository.save(user);

        warehouseSet.clear();
        return new Result("User added",true);
    }

    public Result edit(Integer id,UserDto userDto){
        boolean b = userRepository.existsByPhoneNumberAndIdNot(userDto.getPhoneNumber(), id);
        if (b)
            return new Result("This type phone number such us user already exist",false);
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("User not found",false);
        warehouseSet.clear();
        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        List<Integer> warehousesIds = userDto.getWarehousesIds();
        for (Integer integer : warehousesIds) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(integer);
            optionalWarehouse.ifPresent(warehouse -> warehouseSet.add(warehouse));
        }
        user.setWarehouses(warehouseSet);
        User savedUser = userRepository.save(user);
        warehousesIds.clear();
        return new Result("User edited",true,savedUser);
    }

    public Result delete(Integer id){
        try {
            userRepository.deleteById(id);
            return new Result("User deleted",true);
        }catch (Exception e){
            return new Result("Error in deleting",false);
        }
    }

}
