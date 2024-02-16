import { State } from './IState';

export interface Address {
  id?: number;
  street: string;
  number: string;
  postalCode: string;
  city: string;
  state: Partial<State>;
}
