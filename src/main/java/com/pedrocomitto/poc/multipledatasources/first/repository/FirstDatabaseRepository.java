package com.pedrocomitto.poc.multipledatasources.first.repository;

import com.pedrocomitto.poc.multipledatasources.first.domain.entity.FirstDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstDatabaseRepository extends JpaRepository<FirstDatabaseEntity, Long> {
}
