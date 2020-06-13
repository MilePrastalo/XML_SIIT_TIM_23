import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PaperUpload } from '../model/paperUpload';
import { Observable } from 'rxjs';
import { PaperView } from '../model/paperView';

@Injectable({
  providedIn: 'root'
})
export class PaperService {

  constructor(private http: HttpClient) { }
  path = 'http://localhost:8080';

  sendPaper(paper: PaperUpload): Observable<void> {
    return this.http.post<void>(this.path + '/api/papers', paper);
  }

  updatePaper(paper: PaperUpload): Observable<void> {
    return this.http.post<void>(this.path + '/api/papers/update', paper);
  }

  getPaper( name: string) {
    const x =  this.http.get(this.path + '/api/papers/' + name, {responseType: 'text' });
    return x;
  }
  getPaperAsText(name: string): Observable<string> {
    return this.http.get(this.path + '/api/papers/asText/' + name, { responseType: 'text' });
  }

  getUserPapers(): Observable<Array<PaperView>> {
    return this.http.get<Array<PaperView>>(this.path + '/api/papers/userPapers');
  }

  getCompletedPapers(): Observable<Array<PaperView>> {
    return this.http.get<Array<PaperView>>(this.path + '/api/papers/completedPapers');
  }


  acceptPaper( paperName: string): Observable<boolean> {
    return this.http.get<boolean>(this.path + '/api/papers/acceptPaper/' + paperName);
  }

  rejectPaper( paperName: string): Observable<boolean> {
    return this.http.get<boolean>(this.path + '/api/papers/rejectPaper/' + paperName);
  }

  deletePaper(paperName: string) {
    return this.http.delete(this.path + '/api/papers/' + paperName);
  }

}
