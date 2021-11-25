package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String name;

    @Override
    public Integer getId() {
        return roleId;
    }

    @Override
    public void setId(Integer id) {
        roleId = id;
    }
}
