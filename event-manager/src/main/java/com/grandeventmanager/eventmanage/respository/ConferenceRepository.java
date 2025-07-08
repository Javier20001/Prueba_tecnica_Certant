package com.grandeventmanager.eventmanage.respository;

import com.grandeventmanager.eventmanage.model.Conference;
import com.grandeventmanager.eventmanage.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {

    List<Conference> findByStatus(String status);
}
