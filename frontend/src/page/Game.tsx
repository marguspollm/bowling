import { useEffect, useState } from "react";
import AddPlayer from "../components/AddPlayer";
import PlayerDetails from "../components/PlayerDetails";
import type { Player, PlayerGame } from "../types";
import "../css/Game.css";

const URL = "http://localhost:8080";

function Game() {
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
      .then(json => setSelectedPlayer(json));
  };

  const onSelectPlayer = (id: string) => {
    fetch(`${URL}/player/${id}`)
      .then(res => res.json())
      .then(json => setSelectedPlayer(json))
      .catch(err => console.log(err));
  };

  useEffect(() => {
    fetch(`${URL}/all-players`)
      .then(res => res.json())
      .then(json => setPlayers(json))
      .catch(err => console.log(err));
  }, []);
  return (
    <>
      <div className="players-container">
        <AddPlayer onSubmit={addPlayer} />

        <div className="player-list">
          {players.map(player => {
            const isSelected = selectedPlayer?.id === player.id;
            return (
              <div
                key={player.id}
                className={`player-item ${isSelected ? "selected" : ""}`}
                onClick={() => player.id && onSelectPlayer(player.id)}
              >
                {player.name}
              </div>
            );
          })}
        </div>
        <PlayerDetails game={selectedPlayer} onAddRoll={addRoll} />
      </div>
    </>
  );
}

export default Game;
