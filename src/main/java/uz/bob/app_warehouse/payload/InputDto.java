package uz.bob.app_warehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputDto {

    private String date;

    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;

    private String factureNumber;

    private String code;


}
