import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./AuthForm.css";
function Register() {
  const [formData, setFormData] = useState({
    attendeeEmail: "",
    attendeeName: "",
    attendeePassword: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:9000/api/session/register",
        formData
      );
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("attendeeId", response.data.idAttendee);
      alert("Registrado con éxito");
      navigate("/home");
    } catch (error: any) {
      const message =
        error.response?.data?.message ||
        error.response?.data ||
        "Error al iniciar sesión";
      alert(message);
    }
  };

  return (
    <div className="auth-form-wrapper">
      <form onSubmit={handleSubmit} className="auth-form-container">
        <h2 className="text-xl font-bold">Registro</h2>
        <input
          type="text"
          name="attendeeName"
          placeholder="Nombre"
          value={formData.attendeeName}
          onChange={handleChange}
          required
          className="p-2 border rounded"
        />
        <input
          type="email"
          name="attendeeEmail"
          placeholder="Correo"
          value={formData.attendeeEmail}
          onChange={handleChange}
          required
          className="p-2 border rounded"
        />
        <input
          type="password"
          name="attendeePassword"
          placeholder="Contraseña"
          value={formData.attendeePassword}
          onChange={handleChange}
          required
          className="p-2 border rounded"
        />
        <button
          type="submit"
          className="bg-blue-500 text-white py-2 px-4 rounded"
        >
          Registrarse
        </button>
        <p className="text-sm">
          ¿Ya tenes una cuenta?{" "}
          <a href="/login" className="text-blue-500 hover:underline">
            Inicia sesion
          </a>
        </p>
      </form>
    </div>
  );
}

export default Register;
