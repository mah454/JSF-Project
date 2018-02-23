package ir.moke.jsf.model.entities;

import org.apache.johnzon.mapper.JohnzonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@MappedSuperclass
@XmlRootElement
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DEFAULT_SEQ")
    @XmlTransient
    @JohnzonIgnore
    private long id;

    @Version
    @XmlTransient
    @JohnzonIgnore
    private long version;

    public BaseEntity(long id, long version) {
        this.id = id;
        this.version = version;
    }

    public BaseEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
