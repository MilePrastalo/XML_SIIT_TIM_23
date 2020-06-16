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

  updatePaper(paper: PaperUpload, paperName: string): Observable<void> {
    return this.http.post<void>(this.path + '/api/papers/update/' + paperName, paper);
  }

  getPaper(name: string) {
    const x = this.http.get(this.path + '/api/papers/' + name, { responseType: 'text' });
    return x;
  }
  getPaperAsText(name: string): Observable<string> {
    return this.http.get(this.path + '/api/papers/asText/' + name, { responseType: 'text' });
  }
  getCoverLetter(name: string): Observable<string> {
    return this.http.get(this.path + '/api/papers/coverLetter/' + name, { responseType: 'text' });
  }

  getUserPapers(): Observable<Array<PaperView>> {
    return this.http.get<Array<PaperView>>(this.path + '/api/papers/userPapers');
  }

  getCompletedPapers(): Observable<Array<PaperView>> {
    return this.http.get<Array<PaperView>>(this.path + '/api/papers/completedPapers');
  }


  acceptPaper(paperName: string): Observable<boolean> {
    return this.http.get<boolean>(this.path + '/api/papers/acceptPaper/' + paperName);
  }

  rejectPaper(paperName: string): Observable<boolean> {
    return this.http.get<boolean>(this.path + '/api/papers/rejectPaper/' + paperName);
  }

  getAllPublishedPapers(): Observable<Array<PaperView>> {
    return this.http.get<Array<PaperView>>(this.path + '/api/papers/publishedPapers', { headers: this.headers});
  }

  search( dto: SearchDto ): Observable<Array<PaperView>> {
    return this.http.post<Array<PaperView>>(this.path + '/api/papers/searchByMetadata', dto, { headers: this.headers});
  }

  searchByText(dto: SearchDto ) {
    return this.http.post<Array<PaperView>>(this.path + '/api/papers/searchByText', dto, { headers: this.headers});
  }

  deletePaper(paperName: string) {
    return this.http.delete(this.path + '/api/papers/' + paperName);
  }

  sendToEditor(paperName: string) {
    return this.http.put(this.path + '/api/papers/sendToEditor/' + paperName, {});
  }

}
