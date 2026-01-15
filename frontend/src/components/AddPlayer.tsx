import { useState } from "react";
import "../css/AddPlayer.css";

type AddPlayerProps = {
  onSubmit: (name: string) => void;
};

function AddPlayer({ onSubmit }: AddPlayerProps) {
  const [name, setName] = useState("");

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(name.trim());
    setName("");
  };

  return (
    <form onSubmit={handleSubmit} className="add-player-form">
      <input
        type="text"
        value={name}
        required
        onChange={handleChange}
        className="add-player-input"
      />
      <button type="submit" className="add-player-button">
        Add Player
      </button>
    </form>
  );
}

export default AddPlayer;
