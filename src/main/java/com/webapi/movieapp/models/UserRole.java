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
@Table(name = "userRole")
public class UserRole implements BaseEntity<UserRoleId> {
    @EmbeddedId
    @Column(name = "userRoleId")
    private UserRoleId userRoleId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "roleId")
    private Role role;

    @Override
    public UserRoleId getId() {
        return userRoleId;
    }

    @Override
    public void setId(UserRoleId id) {
        userRoleId = id;
    }
}
