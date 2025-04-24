package com.company.patien.repository;

import com.company.patien.entity.InstrumentalExaminationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentalExaminationsRepository extends JpaRepository<InstrumentalExaminationsEntity, Long> {
}
