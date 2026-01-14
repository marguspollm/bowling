import { useState } from "react";
import AddPlayer from "./assets/components/AddPlayer";
import PlayerDetails from "./assets/components/PlayerDetails";
import type { Player, PlayerGame } from "./types";

const URL = "http://localhost:8080";

function App() {
  const [players, setPlayers] = useState<Player[]>([]);
  const [selectedPlayer, setSelectedPlayer] = useState<PlayerGame | null>(null);

  const addPlayer = (name: string) => {
    const payload = {
      name: name,
    };
    fetch(`${URL}/player`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    })
      .then(res => res.json())
      .then(json => setPlayers(prev => [...prev, json]));
  };

  const addRoll = (pins: number) => {
    const payload = {
      pins: pins,
      playerId: selectedPlayer?.id,
    };

    fetch(`${URL}/roll`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    })
      .then(res => res.json())
      .then(json => console.log(json));
  };

  const onSelectPlayer = (id: string) => {
    fetch(`${URL}/player/${id}`)
      .then(res => res.json())
      .then(json => setSelectedPlayer(json))
      .catch(err => console.log(err));
  };

  return (
    <>
      <AddPlayer onSubmit={addPlayer} />

      {players.map(player => {
        return (
          <div
            key={player.id}
            onClick={() => player.id && onSelectPlayer(player.id)}
          >
            {player.name}
          </div>
        );
      })}

      <PlayerDetails game={selectedPlayer} onAddRoll={addRoll} />
    </>
  );
}

export default App;
