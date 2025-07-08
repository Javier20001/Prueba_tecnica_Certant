import ReservationsList from "../components/reservation/ReservationList";
import "./PageBackgroud.css";

const ReservationPage = () => {
  return (
    <div className="reservation-bg">
      <h1 className="reservation-title">Mis Reservas</h1>
      <p className="reservation-desc">
        Aquí puedes ver y gestionar tus reservas de eventos.
      </p>
      <ReservationsList />
    </div>
  );
};

export default ReservationPage;
