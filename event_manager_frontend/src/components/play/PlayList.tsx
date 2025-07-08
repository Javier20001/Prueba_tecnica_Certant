import { useEffect, useState } from "react";
import type { EventInterface } from "../../model/EventInterface";
import { getRecitals } from "../../service/recitalService";
import ListGeneralEvent from "../list/ListGeneralEvent";
import ButtonModal from "../modal/ButtonModal";

const PlayList = () => {
  const [recitals, setRecitals] = useState<EventInterface[]>([]);

  const isAdmin = localStorage.getItem("token") === "ADMIN";

  useEffect(() => {
    getRecitals()
      .then((res) => setRecitals(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      {isAdmin && <ButtonModal origin="play" />}
      <ListGeneralEvent events={recitals} origin="play" />
    </div>
  );
};

export default PlayList;
