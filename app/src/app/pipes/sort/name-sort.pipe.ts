import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'nameSort',
})
export class NameSortPipe implements PipeTransform {
  transform(array: any[], sort: string): any[] {
    if (!sort) return array;

    if (sort === 'Ascending') {
      // Order by name ascending
      return array.slice().sort((a, b) => a.name.localeCompare(b.name));
    } else if (sort === 'Descending') {
      // Order by name descending
      return array.slice().sort((a, b) => b.name.localeCompare(a.name));
    } else {
      return array;
    }
  }
}
