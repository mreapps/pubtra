package com.mreapps.repository.jpa.entity;

import com.mreapps.repository.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractJpaBaseEntity implements BaseEntity
{
    private static final long serialVersionUID = -6469371632410572812L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false, unique = true)
    private Long id;

    @Override
    public final Long getId()
    {
        return id;
    }
}
