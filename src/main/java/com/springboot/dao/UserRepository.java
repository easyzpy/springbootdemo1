package com.springboot.dao;


import com.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    @Query("select t from User t where t.name=:name")
    User findUserByName(@Param("name")String name);
}
