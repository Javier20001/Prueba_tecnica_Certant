import type { TicketType } from "./TicketType";

export interface EventInterface {
  ticketType: TicketType;
  eventName: string;
  eventDate: string;
  capacity: number;
  id: number;
}
