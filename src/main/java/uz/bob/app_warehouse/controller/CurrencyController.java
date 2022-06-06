package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Currency;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public List<Currency> get(){
        List<Currency> currencies = currencyService.get();
        return currencies;
    }

    @GetMapping("/{id}")
    public Currency getById(@PathVariable Integer id){
        Currency currency = currencyService.getById(id);
        return currency;
    }

    @PostMapping
    public Result add(@RequestBody Currency currency){
        return currencyService.add(currency);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Currency currency){
        return currencyService.edit(id, currency);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return currencyService.delete(id);
    }
}
