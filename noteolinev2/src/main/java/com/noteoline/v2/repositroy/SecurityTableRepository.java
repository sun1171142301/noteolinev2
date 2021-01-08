package com.noteoline.v2.repositroy;


import com.noteoline.v2.entity.SecurityTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecurityTableRepository extends  JpaRepository<SecurityTable,Integer>{

   @Query(value = "select * from securitytable where username=?1",nativeQuery = true)
    public List<SecurityTable> findAllByNameUserList(String name);

    @Query(value = "select * from securitytable where role=?1",nativeQuery = true)
    public List<SecurityTable> findAllByRoleList(String role);
}












