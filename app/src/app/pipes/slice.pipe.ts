import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'slice',
})
export class SlicePipe implements PipeTransform {
  transform(array: any[], currentPage: number, itemsPerPage: number): any[] {
    if (!currentPage || !itemsPerPage) {
      console.error('SlicePipe: currentPage and itemsPerPage must be defined.');
      return array;
    }

    const start = (currentPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    return array.slice(start, end);
  }
}
