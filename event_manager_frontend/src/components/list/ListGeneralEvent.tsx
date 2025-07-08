import type { EventInterface } from "../../model/EventInterface";
import ButtonModal from "../modal/ButtonModal";
import "./ListGeneralEvent.css";

interface ListGeneralEventProps {
  events: EventInterface[];
  origin: string;
}

const ListGeneralEvent: React.FC<ListGeneralEventProps> = ({
  events,
  origin,
}) => {
  return (
    <div>
      <ul className="event-list">
        {events.map((event) => (
          <li key={event.id} className="event-card">
            <div className="event-info">
              <strong>{event.eventName}</strong>
              <div>Fecha: {new Date(event.eventDate).toLocaleString()}</div>
              <div>Capacidad: {event.capacity}</div>
            </div>
            <ButtonModal event={event} origin={origin} />
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListGeneralEvent;
