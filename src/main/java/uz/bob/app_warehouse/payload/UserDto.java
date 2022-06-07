package uz.bob.app_warehouse.payload;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private List<Integer> warehousesIds;
/*
* {
 "firstName":"Bobur",
 "lastName":"Borataliyev",
 "phoneNumber":"+998 93 311 12 13",
 "password":"1111",
 "warehousesIds":[1,2]
}*/
}
