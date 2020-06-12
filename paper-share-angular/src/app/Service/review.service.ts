import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PaperUpload } from '../model/paperUpload';
import { Observable } from 'rxjs';
import { AssignReview } from '../model/assignReview';
import { ReviewView } from '../model/reviewView';
import { SendReview } from '../model/SendReview';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }
  path = 'http://localhost:8080';
  headers: HttpHeaders = new HttpHeaders({
    Authorization: 'Bearer ' + localStorage.getItem('token')
  });

  getReview(name: string) {
    return this.http.get(this.path + '/api/review/' + name, { headers: this.headers, responseType: 'text' });
  }

  sendReview(review: SendReview): Observable<void> {
    return this.http.post<void>(this.path + '/api/review/send', review);
  }

  assignReview(assignReviewDto: AssignReview): Observable<string> {
    return this.http.post(this.path + '/api/review', assignReviewDto, { headers: this.headers, responseType: 'text' });
  }

  getUserReviews(): Observable<ReviewView[]> {
    return this.http.get<ReviewView[]>(this.path + '/api/review/userReviews', { headers: this.headers });
  }
  getReviewAsText(name: string): Observable<string> {
    return this.http.get(this.path + '/api/review/asText/' + name, { headers: this.headers, responseType: 'text' });
  }

  getPaperReviews( paperName: string ): Observable<ReviewView[]>  {
    return this.http.get<ReviewView[]>(this.path + '/api/review/paperReviews/' + paperName, { headers: this.headers });
  }

}
