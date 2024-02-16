import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter',
})
export class SearchFilterPipe implements PipeTransform {
  transform(array: any[], searchTerm: any): any {
    if (!searchTerm) {
      return array;
    }

    return array.filter((item) =>
      JSON.stringify(item).toLowerCase().includes(searchTerm.toLowerCase())
    );
  }
}
