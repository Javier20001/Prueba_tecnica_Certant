import { useState } from "react";
import type { EventInterface } from "../../model/EventInterface";
import type { EventDTO } from "../../model/dtos/EventDTO";

import { updatePlay } from "../../service/playService";
import { updateRecital } from "../../service/recitalService";
import { updateConference } from "../../service/conferenceService";

import { useNavigate } from "react-router-dom";

import "./FormStyles.css";

interface FormEventAdminProps {
  event: EventInterface;
  origin: string;
}

const FormEventAdmin: React.FC<FormEventAdminProps> = ({ event, origin }) => {
  const [form, setForm] = useState<EventDTO>({
    eventName: event.eventName ?? "",
    eventDate: event.eventDate ?? "",
    capacity: event.capacity ?? 0,
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
        await updatePlay(event.id, form);
      } else if (origin === "recital") {
        console.log(origin);
        await updateRecital(event.id, form);
      } else if (origin === "conference") {
        await updateConference(event.id, form);
      }
      alert("Evento actualizado");
      navigate("/" + origin + "s");
    } catch (error: any) {
      console.error(error);
      const message =
        error.response?.data?.message ||
        error.response?.data ||
        "Error al actualizar el evento";
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
      <label>
        Fecha:
        <input
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
          Guardar
        </button>
      </div>
    </form>
  );
};

export default FormEventAdmin;
