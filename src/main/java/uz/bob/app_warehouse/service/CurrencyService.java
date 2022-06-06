package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.app_warehouse.entity.Currency;

import uz.bob.app_warehouse.entity.template.AbsEntity;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> get(){
        return currencyRepository.findAll();
    }

    public Currency getById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.orElseGet(Currency::new);
    }

    public Result add(Currency comeCurrency){
        boolean b = currencyRepository.existsByName(comeCurrency.getName());
        if (b)
            return new Result("This currency name exist",false);
        // TODO: 6/6/2022 question: how to set fields to class, its class extends from abstract class
        Currency currency=new Currency();
        currency.setName(comeCurrency.getName());
        currencyRepository.save(currency);
        return new Result("Currency added",true);
    }

    public Result edit(Integer id,Currency currency){
        boolean existsByNameAndIdNot = currencyRepository.existsByNameAndIdNot(currency.getName(),id);
        if (existsByNameAndIdNot)
            return new Result("Currency already exist",false);
//        boolean existsByName = currencyRepository.existsByName(currency.getName());
//        if (existsByName)
//            return new Result("Currency already exist",false);
        if (!currencyRepository.findById(id).isPresent()) {
            return new Result("Currency not found",false);
        }
        Currency editingCurrency = currencyRepository.getReferenceById(id);
        editingCurrency.setName(currency.getName());
        Currency savedCurrency = currencyRepository.save(editingCurrency);
        return new Result("Currency edited",true,savedCurrency);
    }

    public Result delete(Integer id){
        try {
            currencyRepository.deleteById(id);
            return new Result("Currency deleted",true);
        }catch (Exception e){
            return new Result("Error in deleting",false);
        }

    }
}
