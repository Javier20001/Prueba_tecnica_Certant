package com.grandeventmanager.eventmanage.respository;

import com.grandeventmanager.eventmanage.model.Recital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecitalRepository extends JpaRepository<Recital, Long> {
    List<Recital> findByStatus(String status);
}
