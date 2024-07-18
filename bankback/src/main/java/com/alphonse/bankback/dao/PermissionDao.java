package com.alphonse.bankback.dao;

import com.alphonse.bankback.entities.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionDao extends CrudRepository<Permission, Long>{
}
