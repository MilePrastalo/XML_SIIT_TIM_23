import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LogIn } from 'src/model/login.model';
import { Observable } from 'rxjs';
import { LoginJwt } from 'src/model/LoginJwt.model';
import { User } from 'src/model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  private path = 'http://localhost:8080';


  registerUser(user: User): Observable<User> {
    return this.http.post<User>(this.path + '/api/user/register', user);
  }

  login(dto: LogIn): Observable<LoginJwt> {
    return this.http.post<LoginJwt>(this.path + '/api/user/login', dto);
  }

}
