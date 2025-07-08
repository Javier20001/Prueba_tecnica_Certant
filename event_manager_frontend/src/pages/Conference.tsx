import "./PageBackgroud.css";
import ConferenceList from "../components/conference/ConferenceList";

function ConferencePage() {
  return (
    <div className="conference-bg">
      <h2 className="conference-title">Conferencias</h2>
      <p className="conference-desc">
        Estos son nuestros conferencias, ¡reserva antes de que se agoten!
      </p>
      <ConferenceList />
    </div>
  );
}

export default ConferencePage;
