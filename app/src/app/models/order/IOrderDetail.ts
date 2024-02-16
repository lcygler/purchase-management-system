import { Product } from '../product/IProduct';
import { Order } from './IOrder';

export interface OrderDetail {
  id?: number;
  order?: Partial<Order>;
  product: Partial<Product>;
  quantity: number;
  price: number;
}
