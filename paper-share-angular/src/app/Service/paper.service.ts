import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PaperUpload } from '../model/paperUpload';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaperService {

  constructor(private http: HttpClient) { }
  path = 'http://localhost:8080';
  headers: HttpHeaders = new HttpHeaders({
    Authorization: 'Bearer ' + localStorage.getItem('token')
  });

  sendPaper(paper: PaperUpload): Observable<void> {
    return this.http.post<void>(this.path + '/api/papers', paper);
  }
  sendReview(paper: PaperUpload): Observable<void> {
    return this.http.post<void>(this.path + '/api/reviews', paper);
  }

  getPaper( name: string) {
    const x =  this.http.get(this.path + '/api/papers/' + name, { headers: this.headers, responseType: 'text' });
    return x;
  }
}
