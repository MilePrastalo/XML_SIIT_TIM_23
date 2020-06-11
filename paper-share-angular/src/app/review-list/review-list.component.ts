import { Component, OnInit } from '@angular/core';
import { ReviewView } from '../model/reviewView';
import { ReviewService } from '../Service/review.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {

  public userReviews: Array<ReviewView>;
  public role: string;

  constructor(private reviewService: ReviewService, private router: Router) { }

  ngOnInit(): void {

    this.role = localStorage.getItem('role');
    if (this.role === 'ROLE_USER') {
      this.getUserPapers();
    }
  }

  getUserPapers() {
    this.reviewService.getUserReviews().subscribe(
      (response => {
        if (response !== null) {
          this.userReviews = response;
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

}
