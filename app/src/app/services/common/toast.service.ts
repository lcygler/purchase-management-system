import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  constructor() {}

  showSuccessToast(message: string): void {
    Swal.fire({
      title: 'Éxito',
      text: message,
      icon: 'success',
      timer: 2500,
      timerProgressBar: true,
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
    });
  }

  showErrorToast(message: string): void {
    Swal.fire({
      title: 'Error',
      text: message,
      icon: 'error',
      timer: 2500,
      timerProgressBar: true,
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
    });
  }
}
