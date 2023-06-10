package Hibernate;

import jakarta.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "Shop_producer")
public class ShopProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;


    public ShopProducer() {
    }

    public ShopProducer(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}