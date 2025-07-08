package com.grandeventmanager.eventmanage.respository;

import com.grandeventmanager.eventmanage.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.event.id = :eventId AND r.reservationDate > :newDate")
    boolean existsReservationsAfterDate(@Param("eventId") Long eventId, @Param("newDate") LocalDateTime newDate);


}
