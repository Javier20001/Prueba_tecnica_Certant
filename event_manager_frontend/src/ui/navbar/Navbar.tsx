import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  const navigate = useNavigate();

  const isAuthenticated = !!localStorage.getItem("token");
  const isAdmin = localStorage.getItem("token") === "ADMIN";

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("attendeeId");
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <ul className="navbar-list">
        <li>
          <Link to="/home" className="navbar-link">
            Home
          </Link>
        </li>
        <li>
          <Link to="/recitals" className="navbar-link">
            Recitales
          </Link>
        </li>
        <li>
          <Link to="/plays" className="navbar-link">
            Obras
          </Link>
        </li>
        <li>
          <Link to="/conferences" className="navbar-link">
            Conferencias
          </Link>
        </li>
        <li>
          {isAuthenticated && !isAdmin && (
            <Link to="/reservations" className="navbar-link">
              Reservas
            </Link>
          )}
        </li>
        <li>
          {isAuthenticated ? (
            <button onClick={handleLogout} className="navbar-button">
              Logout
            </button>
          ) : (
            <Link to="/login" className="navbar-link">
              Login
            </Link>
          )}
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;
