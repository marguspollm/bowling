export type Player = {
  id?: string;
  name: string;
};

export type PlayerGame = {
  id: string;
  name: string;
  bowling: Bowling;
};

export type Bowling = {
  frames: Frame[];
  totalScore: number;
};

export type Frame = {
  rolls: number[];
  score: number;
  spare: boolean;
  strike: boolean;
};
