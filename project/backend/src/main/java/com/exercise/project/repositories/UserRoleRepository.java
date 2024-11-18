package com.exercise.project.repositories;

import com.exercise.project.entities.UserRole;
import com.exercise.project.entities.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

}
