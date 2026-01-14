import type { PlayerGame } from "../../types";
import AddRoll from "./AddRoll";

type Props = {
  game: PlayerGame | null;
  onAddRoll: (pins: number) => void;
};

function PlayerDetails({ game, onAddRoll }: Props) {
  if (!game) return <p>Select a player</p>;

  return (
    <div>
      <h2>{game.name}</h2> <p>Total Score: {game.bowling.totalScore}</p>
      <AddRoll onSubmit={onAddRoll} /> <h3>Frames</h3>
      <ul>
        {game.bowling.frames.map((f, i) => (
          <li key={i}>
            Frame {i + 1}: Rolls [{f.rolls.join(", ")}], Score: {f.score}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default PlayerDetails;
