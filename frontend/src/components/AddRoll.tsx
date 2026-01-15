import { useState } from "react";
import "../css/AddRoll.css";

type AddRollProps = {
  onSubmit: (pins: number) => void;
};

function AddRoll({ onSubmit }: AddRollProps) {
  const [pins, setPins] = useState("");
  const [buttonDisabled, setButtonDisabled] = useState(true);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const value = event.target.value;
    const nr = Number(event.target.value);
    console.log(nr);
    if (nr <= 10 && nr >= 0) {
      setPins(value);
      setButtonDisabled(false);
    } else {
      setButtonDisabled(true);
    }
  };

  const submit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(Number(pins));
    setPins("");
    setButtonDisabled(true);
  };

  return (
    <form className="add-roll-form" onSubmit={submit}>
      <input
        type="text"
        value={pins}
        onChange={handleChange}
        className="add-roll-input"
      />
      <button
        type="submit"
        className="add-roll-button"
        disabled={buttonDisabled}
      >
        Add Roll
      </button>
    </form>
  );
}

export default AddRoll;
