package com.pedrocomitto.poc.multipledatasources.second.repository;

import com.pedrocomitto.poc.multipledatasources.second.domain.entity.SecondDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondDatabaseRepository extends JpaRepository<SecondDatabaseEntity, Long> {
}
