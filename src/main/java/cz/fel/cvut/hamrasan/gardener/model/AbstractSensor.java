package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractSensor<T> extends AbstractEntity{

    @Basic(optional = false)
    @Column(nullable = false)
    protected LocalDateTime date;

    @Basic(optional = false)
    @Column(nullable = false)
    protected T value;
}
