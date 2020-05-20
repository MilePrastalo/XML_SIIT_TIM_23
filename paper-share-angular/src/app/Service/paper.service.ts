import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaperUpload } from '../model/paperUpload';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaperService {

  constructor(private http: HttpClient) { }
  path = "http://localhost:8080";

  sendPaper(paper: PaperUpload): Observable<void> {
    return this.http.post<void>(this.path + "/api/papers", paper);
  }
}
