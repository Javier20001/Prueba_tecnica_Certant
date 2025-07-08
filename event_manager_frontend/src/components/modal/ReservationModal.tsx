import type { ReservationDTO } from "../../model/dtos/ReservationDTO";
import type { EventInterface } from "../../model/EventInterface";
import FormNewReservation from "../form/FormNewReservation";
import FormUpdateReservation from "../form/FormUpdateReservation";

interface ReservationModalProps {
  isOpen: boolean;
  onClose: () => void;
  event?: EventInterface;
  reservation?: ReservationDTO;
}

const ReservationModal: React.FC<ReservationModalProps> = ({
  isOpen,
  onClose,
  event,
  reservation,
}) => {
  if (!isOpen) return null;

  return (
    <div style={styles.overlay}>
      <div style={styles.modal}>
        <button onClick={onClose} style={styles.closeBtn}>
          X
        </button>
        {event && <FormNewReservation event={event} />}
        {reservation && <FormUpdateReservation reservation={reservation} />}
      </div>
    </div>
  );
};

const styles = {
  overlay: {
    position: "fixed" as const,
    top: 0,
    left: 0,
    width: "100vw",
    height: "100vh",
    backgroundColor: "rgba(0,0,0,0.5)",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    zIndex: 1000,
  },
  modal: {
    backgroundColor: "white",
    padding: "2rem",
    borderRadius: "8px",
    position: "relative" as const,
    minWidth: "300px",
  },
  closeBtn: {
    position: "absolute" as const,
    top: "0.5rem",
    right: "0.5rem",
    background: "transparent",
    border: "none",
    fontSize: "1.2rem",
    cursor: "pointer",
    color: "#333",
  },
};

export default ReservationModal;
