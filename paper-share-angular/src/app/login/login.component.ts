import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
<<<<<<< HEAD
import { UserService } from '../user.service';
=======
import { UserService } from 'src/app/Service/user.service';
>>>>>>> refs/remotes/origin/master
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { LogIn } from 'src/model/login.model';
import { JwtHelperService } from '@auth0/angular-jwt';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private userService: UserService,
    private snackBar: MatSnackBar, private router: Router) { }



  loginForm: FormGroup;
  hide = true;
  token: any;


  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  get username() { return this.loginForm.controls.username.value as string; }
  get password() { return this.loginForm.controls.password.value as string; }


  onLogInSubmit() {
    const loginData = new LogIn(this.username, this.password);
    this.userService.login(loginData).subscribe(
      (response => {
        console.log(response);
        if (response != null) {
          localStorage.setItem('token', response.token);
          const jwt: JwtHelperService = new JwtHelperService();
          const info = jwt.decodeToken(response.token);
          console.log(info);
          const role = info.role[0].authority;
          localStorage.setItem('role', info.role[0].authority);
          this.snackBar.open('Logged In successfully.');

          if (role === 'ROLE_USER') {
            this.router.navigateByUrl('/user-profile');
          } else if (role === 'ROLE_ADMIN') {
            this.router.navigateByUrl('/admin-profile');
          }
        }
      }),
      (error => {
        this.snackBar.open(error.error.message);
      }));
  }


}
