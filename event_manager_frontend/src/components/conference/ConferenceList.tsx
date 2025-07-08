import { useEffect, useState } from "react";
import type { EventInterface } from "../../model/EventInterface";
import ListGeneralEvent from "../list/ListGeneralEvent";
import { getConferences } from "../../service/conferenceService";
import ButtonModal from "../modal/ButtonModal";

const ConferenceList = () => {
  const [conferences, setConferences] = useState<EventInterface[]>([]);
  const isAdmin = localStorage.getItem("token") === "ADMIN";

  useEffect(() => {
    getConferences()
      .then((res) => setConferences(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      {isAdmin && <ButtonModal origin="conference" />}

      <ListGeneralEvent events={conferences} origin="conference" />
    </div>
  );
};

export default ConferenceList;
