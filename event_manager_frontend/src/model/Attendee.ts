import type { Reservation } from "./Reservation";

export interface Attendee {
  id: number;
  attendeeName: string;
  attendeeEmail: string;
  reservations: Reservation[];
  hasFreePass: boolean;
}
