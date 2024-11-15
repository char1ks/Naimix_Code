package com.example.NimixHack.Repository;

import com.example.NimixHack.Model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface rateRepository extends JpaRepository<Rate,Integer> {
    Rate findByEmployeeId(int employeeId);
}
