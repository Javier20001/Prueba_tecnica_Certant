import type { TicketType } from "../TicketType";

export interface ReservationDTO {
  id: number;
  idEvent: number;
  idAttendee: number;
  ticketType: TicketType;
  reservationDate: string;
  nameEvent: string;
}
