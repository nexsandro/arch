package com.jlabs.view.model;

import javax.persistence.*;

@Entity
@Table( name = "TB_TERM")
public class Term {

    @Id
    @SequenceGenerator(name = "SEQ_TERM_GENERATOR", sequenceName = "SE_TERM", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TERM_GENERATOR")
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private Integer name;

    public Term() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }
}
