package uz.bob.app_warehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.bob.app_warehouse.entity.template.AbsEntity;

import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsEntity {


}
