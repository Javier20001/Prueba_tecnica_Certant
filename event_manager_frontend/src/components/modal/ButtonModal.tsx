import { useState } from "react";
import ReservationModal from "./ReservationModal";
import type { EventInterface } from "../../model/EventInterface";
import type { ReservationDTO } from "../../model/dtos/ReservationDTO";
import EventModal from "./EventModal";

interface ButtonModalProps {
  event?: EventInterface;
  reservation?: ReservationDTO;
  origin?: string;
}

const ButtonModal: React.FC<ButtonModalProps> = ({
  event,
  reservation,
  origin,
}) => {
  const [showModal, setShowModal] = useState(false);

  const isAuthenticated = !!localStorage.getItem("token");

  const openModal = () => setShowModal(true);
  const closeModal = () => setShowModal(false);
  const isAdmin = localStorage.getItem("token") === "ADMIN";

  return (
    isAuthenticated && (
      <div style={{ display: "inline-block", marginLeft: "1rem" }}>
        {isAdmin ? (
          event ? (
            <button onClick={openModal}>Editar Evento</button>
          ) : (
            <button onClick={openModal}>Crear Evento</button>
          )
        ) : reservation ? (
          <button onClick={openModal}>Ver Reserva</button>
        ) : event && event.capacity > 0 ? (
          <button onClick={openModal}>Reservar</button>
        ) : (
          <button
            disabled
            style={{ backgroundColor: "gray", cursor: "not-allowed" }}
          >
            No hay entradas disponibles
          </button>
        )}

        {showModal && isAdmin && (
          <EventModal
            event={event}
            isOpen={showModal}
            onClose={closeModal}
            origin={origin}
          />
        )}
        {showModal && !isAdmin && event && (
          <ReservationModal
            isOpen={showModal}
            onClose={closeModal}
            event={event}
          />
        )}
        {showModal && !isAdmin && reservation && (
          <ReservationModal
            isOpen={showModal}
            onClose={closeModal}
            reservation={reservation}
          />
        )}
      </div>
    )
  );
};

export default ButtonModal;
