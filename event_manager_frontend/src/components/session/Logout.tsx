import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Logout = () => {
  const navigate = useNavigate();

  useEffect(() => {
    localStorage.removeItem("token"); // Elimina el token
    localStorage.removeItem("attendeeId"); // Elimina el ID del asistente
    navigate("/login"); // Redirige al login
  }, [navigate]);

  return null; // No renderiza nada
};

export default Logout;
