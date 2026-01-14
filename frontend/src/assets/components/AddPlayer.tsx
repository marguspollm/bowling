import { useState } from "react";

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
    <form onSubmit={handleSubmit}>
      <input type="text" value={name} required onChange={handleChange} />
      <button type="submit">Add</button>
    </form>
  );
}

export default AddPlayer;
