package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.User;
import com.webapi.movieapp.models.UserRole;
import com.webapi.movieapp.models.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    @Transactional
    @Modifying
    void deleteByUser_UserId(Integer userId);

    List<UserRole> findByUser(User user);
}
