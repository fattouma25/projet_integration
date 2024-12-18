package com.example.driver.repository;

import com.example.driver.models.Driver;


import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepository extends CrudRepository<Driver, Integer> {

    public Driver findByEmail(String email);

    public List<Driver> findDriverListByEmail(String email);

    public Driver findByDrivername(String drivername);

    public Driver findByEmailAndPassword(String email, String password);

    public List<Driver> findProfileByEmail(String email);

    @Query(value = "SELECT * FROM driver WHERE drivername LIKE CONCAT('%', ?1, '%') AND address LIKE CONCAT('%', ?2, '%')", nativeQuery = true)
    public List<Driver> findDriversByNameAndAddress(String drivername, String address);

    @Transactional
    @Modifying
    @Query(value = "update driver set status = 'accept' where email = ?1", nativeQuery = true)
    public void updateStatus(String email);

    @Transactional
    @Modifying
    @Query(value = "update driver set status = 'reject' where email = ?1", nativeQuery = true)
    public void rejectStatus(String email);



}
