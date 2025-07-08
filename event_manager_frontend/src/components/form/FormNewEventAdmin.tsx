import { useState } from "react";
import type { EventDTO } from "../../model/dtos/EventDTO";
import { useNavigate } from "react-router-dom";
import { createPlay } from "../../service/playService";
import { createRecital } from "../../service/recitalService";
import { createConference } from "../../service/conferenceService";

import "./FormStyles.css";

interface FormNewEventAdminProps {
  origin: string;
}

const FormNewEventAdmin: React.FC<FormNewEventAdminProps> = ({ origin }) => {
  const [form, setForm] = useState<EventDTO>({
    eventName: "",
    eventDate: "",
    capacity: 0,
  });

  const navigate = useNavigate();

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: name === "capacity" ? Number(value) : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (origin === "play") {
        await createPlay(form);
      } else if (origin === "recital") {
        await createRecital(form);
      } else if (origin === "conference") {
        await createConference(form);
      }
      alert("Evento creado");
      navigate("/" + origin + "s");
    } catch (error: any) {
      console.error(error);
      const message =
        error.response?.data?.message ||
        error.response?.data ||
        "Error al crear el evento";
      alert(message);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <label className="form-label">
        Nombre del evento:
        <input
          className="form-input"
          name="eventName"
          value={form.eventName}
          onChange={handleChange}
          required
        />
      </label>
      <label className="form-label">
        Fecha:
        <input
          className="form-input"
          type="datetime-local"
          name="eventDate"
          value={form.eventDate.slice(0, 16)}
          onChange={handleChange}
          required
        />
      </label>
      <label className="form-label">
        Capacidad:
        <input
          className="form-input"
          type="number"
          name="capacity"
          value={form.capacity}
          onChange={handleChange}
          min={1}
          required
        />
      </label>
      <div>
        <button type="submit" className="form-button">
          Crear
        </button>
      </div>
    </form>
  );
};

export default FormNewEventAdmin;
