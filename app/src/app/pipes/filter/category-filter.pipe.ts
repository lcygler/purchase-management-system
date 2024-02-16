import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../../models/product/IProduct';

@Pipe({
  name: 'categoryFilter',
})
export class CategoryFilterPipe implements PipeTransform {
  transform(array: Product[], filter: string): Product[] {
    if (!filter || filter === 'Categoría') return array;

    return array.filter((item) => {
      if (item.category?.name) {
        return item.category.name.toLowerCase().includes(filter.toLowerCase());
      }
      return false;
    });
  }
}
