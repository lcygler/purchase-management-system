import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'deleteFilter',
})
export class DeleteFilterPipe implements PipeTransform {
  transform(array: any[], filter: boolean): any[] {
    if (filter === null) return array;

    if (filter === true) {
      // Return deleted
      return array.filter((item) => item.isDeleted);
    } else {
      // Return not deleted
      return array.filter((item) => !item.isDeleted);
    }
  }
}
