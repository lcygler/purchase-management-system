import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user/IUser';
import { UserCredential } from 'src/app/models/user/IUserCredential';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ToastService } from 'src/app/services/common/toast.service';
import { UserCredentialService } from 'src/app/services/user/user-credential.service';

@Component({
  selector: 'app-login-pass',
  templateUrl: './login-pass.component.html',
  styleUrls: ['./login-pass.component.css'],
})
export class LoginPassComponent implements OnInit {
  user: User = {
    firstName: '',
    lastName: '',
    dni: '',
    email: '',
    phone: '',
    genre: {},
    address: {},
    role: {},
  };

  userCredential: UserCredential = {
    user: {},
    password: '',
  };

  newPassword: string = '';
  confirmPassword: string = '';
  showNewPassword: boolean = false;
  showConfirmPassword: boolean = false;

  constructor(
    private userCredentialService: UserCredentialService,
    private authService: AuthService,
    private toastService: ToastService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit() {
    this.loadUser();
    this.getUserCredentialByUserId();
  }

  loadUser() {
    this.user = this.authService.loadUser();
  }

  getUserCredentialByUserId() {
    if (this.user.id) {
      this.userCredentialService
        .getUserCredentialByUserId(this.user.id)
        .subscribe((res) => {
          this.userCredential = res;
        });
    }
  }

  onSubmit(form: NgForm) {
    if (form.invalid) {
      console.error('Form contains errors.');
      return;
    }

    const formData = form.value;

    this.userCredentialService
      .updateUserCredential(this.userCredential.id!, {
        password: this.newPassword,
      })
      .subscribe((res) => {
        this.toastService.showSuccessToast('Cambio de contraseña exitoso!');
        this.router.navigate(['/home']);
      });
  }

  toggleShowNewPassword() {
    this.showNewPassword = !this.showNewPassword;
  }

  toggleShowConfirmPassword() {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  navigateBack() {
    this.location.back();
  }
}
