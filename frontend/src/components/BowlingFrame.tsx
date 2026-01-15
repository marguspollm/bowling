import type { Frame } from "../types";
import "../css/BowlingFrame.css";

type BowlingFrameProps = {
  frame: Frame;
  index: number;
};

function BowlingFrame({ frame, index }: BowlingFrameProps) {
  const displayRoll = (frame: Frame) => {
    return frame.rolls.map((r, idx) => {
      let display = String(r);
      if (frame.strike) display = "X";
      if (frame.spare && idx == 1) display = "/";
      return (
        <div key={idx} className="frame-roll">
          {display}
        </div>
      );
    });
  };

  return (
    <div key={index} className="frame">
      <div className="frame-number">Frame {index + 1}</div>
      <div className="frame-rolls">{displayRoll(frame)}</div>
      <div className="frame-score">{frame.score}</div>
    </div>
  );
}

export default BowlingFrame;
