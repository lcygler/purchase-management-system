import { Address } from '../address/IAddress';
import { Genre } from './IGenre';
import { Role } from './IRole';

export interface User {
  id?: number;
  firstName: string;
  lastName: string;
  dni: string;
  email: string;
  phone: string;
  genre: Partial<Genre>;
  address: Partial<Address>;
  role: Partial<Role>;
  isDeleted?: boolean;
}
