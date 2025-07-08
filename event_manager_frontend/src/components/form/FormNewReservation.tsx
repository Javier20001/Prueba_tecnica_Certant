import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createReservation } from "../../service/reservationService";
import type { EventInterface } from "../../model/EventInterface";

import "./FormStyles.css";

interface ReservationFormProps {
  event: EventInterface;
}

const FormNewReservation: React.FC<ReservationFormProps> = ({ event }) => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    id: 0,
    ticketType: event.allowedTickets[0],
    idEvent: event.id,
    idAttendee: Number(localStorage.getItem("attendeeId") || 0),
    reservationDate: new Date().toISOString(),
    nameEvent: event.eventName,
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: name === "idEvent" ? Number(value) : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await createReservation(formData);
      alert("Reserva realizada");
      navigate("/reservations");
    } catch (error: any) {
      console.error(error); // para debug
      const message =
        error.response?.data?.message ||
        error.response?.data ||
        "Error al hacer la reserva";
      alert(message);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <label className="form-label">
        Tipo de Entrada:
        <select
          className="form-input"
          name="ticketType"
          value={formData.ticketType}
          onChange={handleChange}
        >
          {event.allowedTickets.map((ticket) => (
            <option key={ticket} value={ticket}>
              {ticket}
            </option>
          ))}
        </select>
      </label>

      <label className="form-label">
        Fecha de reserva:
        <input
          className="form-input"
          type="datetime-local"
          name="reservationDate"
          value={formData.reservationDate.slice(0, 16)}
          onChange={(e) =>
            setFormData((prev) => ({
              ...prev,
              reservationDate: new Date(e.target.value).toISOString(),
            }))
          }
        />
      </label>

      <button type="submit" className="form-button">
        Reservar
      </button>
    </form>
  );
};

export default FormNewReservation;
