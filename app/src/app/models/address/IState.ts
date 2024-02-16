import { Country } from './ICountry';

export interface State {
  id: number | null;
  name: string;
  country: Partial<Country>;
}
