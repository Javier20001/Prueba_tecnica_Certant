import RecitalList from "../components/recital/RecitalList";
import "./PageBackgroud.css";
function RecitalPage() {
  return (
    <div className="recital-bg">
      <h2 className="recital-title">Recitales</h2>
      <p className="recital-desc">
        Estos son nuestros recitales, ¡reserva antes de que se agoten!
      </p>
      <RecitalList />
    </div>
  );
}

export default RecitalPage;
