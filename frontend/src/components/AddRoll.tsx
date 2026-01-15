import { useState } from "react";
import "../css/AddRoll.css";

type AddRollProps = {
  onSubmit: (pins: number) => void;
};

function AddRoll({ onSubmit }: AddRollProps) {
  const [pins, setPins] = useState(0);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPins(Number(event.target.value));
  };

  const submit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(pins);
    setPins(0);
  };

  return (
    <form className="add-roll-form" onSubmit={submit}>
      <input
        type="number"
        min={0}
        max={10}
        value={pins}
        onChange={handleChange}
      />
      <button type="submit" className="add-roll-button">
        Add Roll
      </button>
    </form>
  );
}

export default AddRoll;
