import axios from "axios";
import type { EventInterface } from "../model/EventInterface";
import type { EventDTO } from "../model/dtos/EventDTO";

export const getPlays = () => {
  return axios.get<EventInterface[]>("http://localhost:9000/api/play");
};

export const updatePlay = (id: number, data: EventDTO) => {
  return axios.patch(`http://localhost:9000/api/play/${id}`, data);
};

export const createPlay = (data: EventDTO) => {
  return axios.post("http://localhost:9000/api/play", data);
};
