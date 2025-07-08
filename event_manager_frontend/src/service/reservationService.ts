import axios from "axios";
import type { ReservationDTO } from "../model/dtos/ReservationDTO";

const API_BASE = "http://localhost:9000/api";

export const getReservationsByAttendee = (attendeeId: string) => {
  return axios.get(`${API_BASE}/reservation/attendee/${attendeeId}`);
};

export const createReservation = (data: ReservationDTO) => {
  return axios.post(`${API_BASE}/reservation`, data);
};

export const updateReservation = (id: number, data: ReservationDTO) => {
  return axios.patch(`${API_BASE}/reservation/${id}`, data);
};
