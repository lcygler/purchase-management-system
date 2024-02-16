import { Pipe, PipeTransform } from '@angular/core';
import { Order } from 'src/app/models/order/IOrder';

@Pipe({
  name: 'issueDateSort',
})
export class IssueDateSortPipe implements PipeTransform {
  transform(array: Order[], sort: string): Order[] {
    if (!sort) return array;

    if (sort === 'Ascending') {
      // Order by issue date ascending
      return array.slice().sort((a, b) => new Date(a.issueDate!).getTime() - new Date(b.issueDate!).getTime());
    } else if (sort === 'Descending') {
      // Order by issue date descending
      return array.slice().sort((a, b) => new Date(b.issueDate!).getTime() - new Date(a.issueDate!).getTime());
    } else {
      return array;
    }
  }
}
