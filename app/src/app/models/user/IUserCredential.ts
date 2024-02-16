import { User } from './IUser';

export interface UserCredential {
  id?: number;
  user: Partial<User>;
  password: string;
}
