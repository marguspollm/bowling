export type Player = {
  id?: string;
  name: string;
};

export type Roll = {
  pins: number;
  id: string;
};

export type Game = {
  id: string;
  frames: Frame[];
  totalScore: number;
  player: Player;
  finished: boolean;
};

export type Frame = {
  rolls: number[];
  score: number;
  spare: boolean;
  strike: boolean;
  complete: boolean;
};
