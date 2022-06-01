package uz.bob.app_warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Timestamp date;//check this timestamp package

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Currency currency;

    @Column(nullable = false)
    private String factureNumber;

    @Column(nullable = false)
    private Integer code;

}
