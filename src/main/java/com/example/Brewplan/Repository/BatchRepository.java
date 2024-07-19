package com.example.Brewplan.Repository;

import com.example.Brewplan.Model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    // Custom query methods if needed
}
