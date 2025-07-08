export interface Reservation {
  id: number;
  reservationDate: string;
  ticketType: string;
  freePass: boolean;
  ticketTypeValid: boolean;
  nameEvent: string;
}
