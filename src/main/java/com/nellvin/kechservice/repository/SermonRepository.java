package com.nellvin.kechservice.repository;

import com.nellvin.kechservice.model.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SermonRepository extends JpaRepository<Sermon,Long> {
}
