import axios from "axios";
import type { Attendee } from "../model/Attendee";

export const getAttendeeById = (attendeeId: string) => {
  return axios.get<Attendee>(
    `http://localhost:9000/api/attendee/${attendeeId}`
  );
};
