import { Supplier } from '../supplier/ISupplier';
import { User } from '../user/IUser';
import { Status } from './IStatus';

export interface Order {
  id?: number;
  number: string;
  issueDate: string | Date | null;
  deliveryDate: string | Date | null;
  comments?: string;
  total: number | null;
  status?: Partial<Status>;
  supplier: Partial<Supplier>;
  user?: Partial<User>;
  isDeleted?: boolean;
}
