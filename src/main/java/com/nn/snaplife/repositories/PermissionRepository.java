package com.nn.snaplife.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nn.snaplife.beans.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
	public Permission findByPermission(String permission);
}
