import { Pipe, PipeTransform } from '@angular/core';
import { Order } from '../../models/order/IOrder';

@Pipe({
  name: 'statusFilter',
})
export class StatusFilterPipe implements PipeTransform {
  transform(array: Order[], filter: string): Order[] {
    if (!filter || filter === 'Estado') return array;

    return array.filter((item) => {
      if (item.status?.name) {
        return item.status.name.toLowerCase().includes(filter.toLowerCase());
      }
      return false;
    });
  }
}
