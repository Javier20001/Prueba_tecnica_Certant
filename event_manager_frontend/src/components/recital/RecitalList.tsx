import { useEffect, useState } from "react";
import type { EventInterface } from "../../model/EventInterface";
import { getRecitals } from "../../service/recitalService";
import ListGeneralEvent from "../list/ListGeneralEvent";
import ButtonModal from "../modal/ButtonModal";

const RecitalList = () => {
  const [recitals, setRecitals] = useState<EventInterface[]>([]);
  const isAdmin = localStorage.getItem("token") === "ADMIN";

  useEffect(() => {
    getRecitals()
      .then((res) => setRecitals(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      {isAdmin && <ButtonModal origin="recital" />}
      <ListGeneralEvent events={recitals} origin="recital" />
    </div>
  );
};

export default RecitalList;
