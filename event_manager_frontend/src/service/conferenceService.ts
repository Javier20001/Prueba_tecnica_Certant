import axios from "axios";
import type { EventInterface } from "../model/EventInterface";
import type { EventDTO } from "../model/dtos/EventDTO";

export const getConferences = () => {
  return axios.get<EventInterface[]>("http://localhost:9000/api/conference");
};

export const updateConference = (id: number, data: EventDTO) => {
  return axios.patch(`http://localhost:9000/api/conference/${id}`, data);
};

export const createConference = (data: EventDTO) => {
  return axios.post("http://localhost:9000/api/conference", data);
};
