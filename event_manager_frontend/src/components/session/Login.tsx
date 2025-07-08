// src/components/Login.tsx
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import "./AuthForm.css";

function Login() {
  const [credentials, setCredentials] = useState({
    attendeeEmail: "",
    attendeePassword: "",
  });

  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCredentials({
      ...credentials,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post<string>(
        "http://localhost:9000/api/session/login",
        credentials
      );

      localStorage.setItem("token", response.data.token);
      localStorage.setItem("attendeeId", response.data.idAttendee);

      alert("Login exitoso");
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
        <h2 className="text-xl font-bold">Iniciar Sesión</h2>
        <input
          type="email"
          name="attendeeEmail"
          placeholder="Correo"
          value={credentials.attendeeEmail}
          onChange={handleChange}
          required
          className="p-2 border rounded"
        />
        <input
          type="password"
          name="attendeePassword"
          placeholder="Contraseña"
          value={credentials.attendeePassword}
          onChange={handleChange}
          required
          className="p-2 border rounded"
        />
        <button
          type="submit"
          className="bg-green-500 text-white py-2 px-4 rounded"
        >
          Ingresar
        </button>
        <p className="text-sm">
          ¿No tienes cuenta?{" "}
          <a href="/register" className="text-blue-500 hover:underline">
            Regístrate
          </a>
        </p>
      </form>
    </div>
  );
}

export default Login;
