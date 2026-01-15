import type { PlayerGame } from "../types";
import AddRoll from "./AddRoll";
import BowlingFrame from "./BowlingFrame";
import "../css/PlayerDetails.css";

type Props = {
  game: PlayerGame | null;
  onAddRoll: (pins: number) => void;
};

function PlayerDetails({ game, onAddRoll }: Props) {
  if (!game) return;

  return (
    <div className="score-container">
      <h2>{game.name}</h2> <p>Total Score: {game.bowling.totalScore}</p>
      <AddRoll onSubmit={onAddRoll} />
      <div className="scoreboard">
        {game.bowling.frames.map((f, i) => (
          <BowlingFrame key={i} frame={f} index={i} />
        ))}
      </div>
    </div>
  );
}

export default PlayerDetails;
