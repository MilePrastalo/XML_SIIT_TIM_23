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

  getReview(name: string) {
    return this.http.get(this.path + '/api/review/' + name, { responseType: 'text' });
  }

  getRecommendedReviewers(title: string): Observable<string[]> {
    return this.http.get<string[]>(this.path + '/api/review/recommendReviewers/' + title);
  }

  getUnitedReview(name: string) {
    return this.http.get(this.path + '/api/review/united/' + name, { responseType: 'text' });
  }

  sendReview(review: SendReview): Observable<void> {
    return this.http.post<void>(this.path + '/api/review/send', review);
  }

  assignReview(assignReviewDto: AssignReview): Observable<string> {
    return this.http.post(this.path + '/api/review', assignReviewDto, { responseType: 'text' });
  }

  getUserReviews(): Observable<ReviewView[]> {
    return this.http.get<ReviewView[]>(this.path + '/api/review/userReviews', );
  }

  getSubmittedReviews(): Observable<ReviewView[]> {
    return this.http.get<ReviewView[]>(this.path + '/api/review/submittedReviews');
  }

  getReviewAsText(name: string): Observable<string> {
    return this.http.get(this.path + '/api/review/asText/' + name, { responseType: 'text' });
  }

  getPaperReviews(paperName: string): Observable<ReviewView[]> {
    return this.http.get<ReviewView[]>(this.path + '/api/review/paperReviews/' + paperName, );
  }
  acceptReview(reviewName: string) {
    return this.http.get(this.path + '/api/review/accept/' + reviewName, { responseType: 'text' });
  }
  rejectReview(reviewName: string) {
    return this.http.get(this.path + '/api/review/reject/' + reviewName, { responseType: 'text' });
  }
  publishReview(reviewName: string) {
    return this.http.get(this.path + '/api/review/publish/' + reviewName, { responseType: 'text' });
  }

  sendReviewsToAuthor(paperName: string) {
    return this.http.post(this.path + '/api/review/sendReviewsToAuthor/' + paperName, {}, { responseType: 'text' });
  }

}
