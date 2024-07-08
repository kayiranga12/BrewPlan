package com.example.Brewplan.Repository;

import com.example.Brewplan.Model.RawMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialsRepository extends JpaRepository<RawMaterials, Long> {
}
