import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PaperUpload } from '../model/paperUpload';
import { Observable } from 'rxjs';
import { PaperView } from '../model/paperView';
import { SearchDto } from '../model/searchDto';

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

  getPaper( name: string) {
    const x =  this.http.get(this.path + '/api/papers/' + name, { headers: this.headers, responseType: 'text' });
    return x;
  }

  getUserPapers(): Observable<Array<PaperView>> {
    return this.http.get<Array<PaperView>>(this.path + '/api/papers/userPapers', { headers: this.headers});
  }

  getCompletedPapers(): Observable<Array<PaperView>> {
    return this.http.get<Array<PaperView>>(this.path + '/api/papers/completedPapers', { headers: this.headers});
  }


  acceptPaper( paperName: string): Observable<boolean> {
    return this.http.get<boolean>(this.path + '/api/papers/acceptPaper/' + paperName, { headers: this.headers});
  }

  rejectPaper( paperName: string): Observable<boolean> {
    return this.http.get<boolean>(this.path + '/api/papers/rejectPaper/' + paperName, { headers: this.headers});
  }

  search( dto: SearchDto ): Observable<Array<PaperView>> {
    return this.http.post<Array<PaperView>>(this.path + '/api/papers/search', dto, { headers: this.headers});
  }

}
