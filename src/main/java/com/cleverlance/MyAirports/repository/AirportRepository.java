package com.cleverlance.MyAirports.repository;

import com.cleverlance.MyAirports.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query(value = "SELECT a FROM Airport a WHERE a.code = ?1")
    Airport getAirportByCode(String code);
}
