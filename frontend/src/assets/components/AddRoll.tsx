import { useState } from "react";

type AddRollProps = {
  onSubmit: (pins: number) => void;
};

function AddRoll({ onSubmit }: AddRollProps) {
  const [pins, setPins] = useState(0);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPins(Number(event.target.value));
  };

  const submit = () => {
    onSubmit(pins);
    setPins(0);
  };

  return (
    <div>
      <input
        type="number"
        min={0}
        max={10}
        value={pins}
        onChange={handleChange}
      />
      <button onClick={submit}>Add Roll</button>
    </div>
  );
}

export default AddRoll;
