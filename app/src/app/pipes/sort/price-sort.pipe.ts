import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../../models/product/IProduct';

@Pipe({
  name: 'priceSort',
})
export class PriceSortPipe implements PipeTransform {
  transform(array: Product[], sort: string): Product[] {
    if (!sort) return array;

    if (sort === 'Ascending') {
      // Order by price ascending
      return array.slice().sort((a, b) => a.price! - b.price!);
    } else if (sort === 'Descending') {
      // Order by price descending
      return array.slice().sort((a, b) => b.price! - a.price!);
    } else {
      return array;
    }
  }
}
