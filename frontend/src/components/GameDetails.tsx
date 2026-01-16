import type { Game } from "../types";
import BowlingFrame from "./BowlingFrame";
import "../css/GameDetails.css";

type GameDetailsProps = {
  game: Game | null;
};

function GameDetails({ game }: GameDetailsProps) {
  if (!game) return;

  return (
    <div className="details-container">
      <div className="details">
        {game.frames.map((f, i) => (
          <BowlingFrame key={i} frame={f} index={i} />
        ))}
      </div>
    </div>
  );
}

export default GameDetails;
