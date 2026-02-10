package com.jose.primer_api.repository;

import com.jose.primer_api.entity.Squads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadsRepository extends JpaRepository<Squads, Long> {
}
