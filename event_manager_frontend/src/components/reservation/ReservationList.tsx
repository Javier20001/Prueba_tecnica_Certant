import { useEffect, useState } from "react";
import { getReservationsByAttendee } from "../../service/reservationService";
import ButtonModal from "../modal/ButtonModal";
import type { ReservationDTO } from "../../model/dtos/ReservationDTO";
import "./ReservationList.css";
import type { Attendee } from "../../model/Attendee";
import { getAttendeeById } from "../../service/attendeeService";

function ReservationsList() {
  const [reservations, setReservations] = useState<ReservationDTO[]>([]);
  const [attendee, setAttende] = useState<Attendee | undefined>(undefined);
  const attendeeId = localStorage.getItem("attendeeId");

  useEffect(() => {
    if (attendeeId) {
      getReservationsByAttendee(attendeeId)
        .then((res) => setReservations(res.data))
        .catch((err) => console.error(err));

      getAttendeeById(attendeeId)
        .then((res) => setAttende(res.data))
        .catch((err) => console.error(err));
    }
  }, [attendeeId]);

  return (
    <div>
      <div className="attendee-info">
        {attendee?.hasFreePass && (
          <p>Tenes un pase Libre en tu proxima reserva</p>
        )}
      </div>
      <ul className="reservation-list">
        {reservations.length === 0 ? (
          <p>No tenés reservas, ANDA A RESERVAR!!!!.</p>
        ) : (
          reservations.map((res) => (
            <li key={res.id} className="reservation-card">
              <div className="reservation-info">
                <strong>{res.nameEvent}</strong>
                <div>Entrada: {res.ticketType}</div>
                <div>
                  Fecha: {new Date(res.reservationDate).toLocaleString()}
                </div>
              </div>
              <ButtonModal reservation={res} />
            </li>
          ))
        )}
      </ul>
    </div>
  );
}

export default ReservationsList;
