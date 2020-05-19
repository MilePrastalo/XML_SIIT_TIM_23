import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { UserService } from '../user.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  hide = true;
  registerBtn = false;
  constructor(private formBuilder: FormBuilder, private userService: UserService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.createRegisterForm();
  }
  get userData(): User {
    const user: User = new User();
    user.username = this.registerForm.controls.username.value;
    user.password = this.registerForm.controls.password.value;
    user.repeatedPassword = this.registerForm.controls.repeatedPassword.value;
    user.firstName = this.registerForm.controls.firstName.value;
    user.lastName = this.registerForm.controls.lastName.value;
    user.email = this.registerForm.controls.email.value;
    return user;
  }

  onRegisterSubmit() {
    const user = this.userData;
    if (user.password !== user.repeatedPassword) {
      this.snackBar.open('Wrong repeated password.');
      return;
    }
    this.userService.registerUser(user).subscribe(
      (response => {
        if (response !== null) {
          this.snackBar.open('Successfuly registred!');
          this.registerBtn = true;
        }
      }),
      (error => {
        this.snackBar.open(error.error.message);
      })
    );
  }

  createRegisterForm() {
    this.registerForm = this.formBuilder.group({
      username: ['', [
        Validators.required,
        Validators.minLength(4),
        Validators.pattern('[a-z A-Z 0-9]*')
      ]],
      password : ['', [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(30),
        Validators.pattern('[a-z A-Z 0-9]*')
      ]],
      repeatedPassword: ['', [
        Validators.required,
      ]],
      firstName: ['', [
        Validators.required,
        Validators.maxLength(20),
        Validators.pattern('[a-z A-Z]*')
      ]],
      lastName: ['', [
        Validators.required,
        Validators.maxLength(20),
        Validators.pattern('[a-z A-Z]*')
      ]],
      email: ['', [
        Validators.required,
        Validators.email
      ]],
    });
  }



}
