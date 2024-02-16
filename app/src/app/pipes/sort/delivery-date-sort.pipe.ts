import { Pipe, PipeTransform } from '@angular/core';
import { Order } from 'src/app/models/order/IOrder';

@Pipe({
  name: 'deliveryDateSort'
})
export class DeliveryDateSortPipe implements PipeTransform {
  transform(array: Order[], sort: string): Order[] {
    if (!sort) return array;

    if (sort === 'Ascending') {
      // Order by delivery date ascending
      return array.slice().sort((a, b) => new Date(a.deliveryDate!).getTime() - new Date(b.deliveryDate!).getTime());
    } else if (sort === 'Descending') {
      // Order by delivery date descending
      return array.slice().sort((a, b) => new Date(b.deliveryDate!).getTime() - new Date(a.deliveryDate!).getTime());
    } else {
      return array;
    }
  }
}
