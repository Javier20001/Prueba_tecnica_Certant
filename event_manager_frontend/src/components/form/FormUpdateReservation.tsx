import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { updateReservation } from "../../service/reservationService";
import type { ReservationDTO } from "../../model/dtos/ReservationDTO";
import "./FormStyles.css";

interface FormUpdateReservationProps {
  reservation: ReservationDTO;
}

const FormUpdateReservation: React.FC<FormUpdateReservationProps> = ({
  reservation,
}) => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState<ReservationDTO>({
    id: reservation.id,
    ticketType: reservation.ticketType,
    idEvent: reservation.idEvent,
    idAttendee: reservation.idAttendee,
    reservationDate: reservation.reservationDate,
    nameEvent: reservation.nameEvent,
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      updateReservation(formData.id, formData);
      alert("Reserva actualizada");
      navigate("/reservations");
    } catch (err) {
      console.error("Error al actualizar reserva:", err);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <label className="form-label">
        Fecha de reserva:
        <input
          type="datetime-local"
          name="reservationDate"
          value={formData.reservationDate.slice(0, 16)}
          onChange={(e) =>
            setFormData((prev) => ({
              ...prev,
              reservationDate: new Date(e.target.value).toISOString(),
            }))
          }
          className="form-input"
        />
      </label>

      <button type="submit" className="form-button">
        Actualizar Reserva
      </button>
    </form>
  );
};

export default FormUpdateReservation;
