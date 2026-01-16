import { useEffect, useState } from "react";
import AddPlayer from "../components/AddPlayer";
import type { Player, Game } from "../types";
import "../css/Game.css";
import AddRoll from "../components/AddRoll";
import GameDetails from "../components/GameDetails";

const URL = "http://localhost:8080";

function Game() {
  const [games, setGames] = useState<Game[]>([]);
  const [selectedGame, setSelectedGame] = useState<Game | null>(null);

  const addPlayer = (name: string) => {
    const payload: Player = {
      name: name,
    };
    fetch(`${URL}/create`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    })
      .then(res => {
        if (!res.ok) throw Error("Can't use same name!");
        return res.json();
      })
      .then(json => setGames(prev => [...prev, json]))
      .catch(err => alert(err));
  };

  const addRoll = (pins: number) => {
    const payload = {
      pins: pins,
      gameId: selectedGame?.id,
    };

    fetch(`${URL}/roll`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    })
      .then(res => {
        if (!res.ok) throw new Error("Too many pins!");
        return res.json();
      })
      .then(json => {
        setGames(json);
      })
      .catch(err => alert(err));
  };

  const onSelectPlayer = (id: string) => {
    const g = games.find(game => game.id === id) ?? null;
    setSelectedGame(g);
  };

  const clearGames = () => {
    const delFetch = async (confirm?: boolean) => {
      let param = "";
      if (confirm) param += "?confirm=true";
      const res = await fetch(`${URL}/del${param}`, {
        method: "DELETE",
      });
      return res;
    };

    delFetch()
      .then(res => {
        if (res.ok) {
          alert("All games are deleted!");
          setGames([]);
        } else {
          if (
            confirm(
              "Some games are still in progress!\n Are you sure you want to delete all games?"
            )
          ) {
            delFetch(true)
              .then(() => setGames([]))
              .catch(() => alert("Something went wrong"));
          } else {
            return;
          }
        }
      })
      .catch(err => confirm(err));
  };

  useEffect(() => {
    fetch(`${URL}/all-games`)
      .then(res => res.json())
      .then(json => {
        setGames(json);
      })
      .catch(err => console.log(err));
  }, []);

  return (
    <>
      <div className="game-container">
        <div className="game-list">
          {games.map(game => {
            const isSelected = selectedGame?.id === game.id;
            return (
              <div
                key={game.id}
                className={`game-item ${isSelected ? "selected" : ""}`}
                onClick={() => game.id && onSelectPlayer(game.id)}
              >
                <div className="game-header">
                  <span className="game-name">{game.player.name}</span>
                  <span className="game-score">Total: {game.totalScore}</span>
                </div>
                <GameDetails game={game} />
                {isSelected && (
                  <div className="game-addroll">
                    <AddRoll onSubmit={addRoll} />
                  </div>
                )}
              </div>
            );
          })}
        </div>
        <AddPlayer onSubmit={addPlayer} />
        {games.length != 0 && (
          <button className="game-clearall" onClick={clearGames}>
            Clear all games
          </button>
        )}
      </div>
    </>
  );
}

export default Game;
