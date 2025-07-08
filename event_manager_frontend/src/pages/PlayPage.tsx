import PlayList from "../components/play/PlayList";
import "./PageBackgroud.css";
function PlayPage() {
  return (
    <div className="play-bg">
      <h2 className="play-title">Obras de Teatro</h2>
      <p className="play-desc">
        Estos son nuestros obras de teatro, ¡reserva antes de que se agoten!
      </p>
      <PlayList />
    </div>
  );
}

export default PlayPage;
