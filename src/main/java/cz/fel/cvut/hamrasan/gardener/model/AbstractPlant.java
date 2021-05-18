package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractPlant extends AbstractEntity{

    @Basic(optional = false)
    @Column(nullable = false)
    protected double minTemperature;

    @Basic(optional = false)
    @Column(nullable = false)
    protected double maxTemperature;

    @Basic(optional = false)
    @Column(nullable = false)
    protected String season;

}
