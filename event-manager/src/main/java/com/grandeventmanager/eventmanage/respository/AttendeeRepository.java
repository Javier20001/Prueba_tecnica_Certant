package com.grandeventmanager.eventmanage.respository;

import com.grandeventmanager.eventmanage.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee,Long> {

    @Query("""
        SELECT COUNT(r)
        FROM Reservation r
        WHERE r.attendee.id = :attendeeId
          AND r.reservationDate BETWEEN :from AND :to
    """)
    int countReservationsLastYear(@Param("attendeeId") Long attendeeId,
                                  @Param("from") LocalDateTime from,
                                  @Param("to") LocalDateTime to);


    Optional<Attendee> findByAttendeeEmailAndAttendeePassword(String email,String password);
}
