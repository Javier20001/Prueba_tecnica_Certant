import axios from "axios";
import type { EventInterface } from "../model/EventInterface";
import type { EventDTO } from "../model/dtos/EventDTO";

export const getRecitals = () => {
  return axios.get<EventInterface[]>("http://localhost:9000/api/recital");
};

export const updateRecital = (id: number, data: EventDTO) => {
  return axios.patch(`http://localhost:9000/api/recital/${id}`, data);
};

export const createRecital = (data: EventDTO) => {
  return axios.post("http://localhost:9000/api/recital", data);
};
