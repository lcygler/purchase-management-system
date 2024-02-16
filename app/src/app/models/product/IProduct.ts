import { Image } from '../common/IImage';
import { Supplier } from '../supplier/ISupplier';
import { Category } from './ICategory';

export interface Product {
  id?: number;
  sku: string;
  name: string;
  description: string;
  price: number | null;
  image?: Partial<Image>;
  category: Partial<Category>;
  supplier: Partial<Supplier>;
  isDeleted?: boolean;
}
