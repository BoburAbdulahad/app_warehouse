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

    @ManyToOne(optional = false)
    private Warehouse warehouse;

    @ManyToOne(optional = false)
    private Client client;

    @ManyToOne(optional = false)
    private Currency currency;

    @Column(nullable = false)
    private String factureNumber;

    @Column(nullable = false)
    private String code;

}
