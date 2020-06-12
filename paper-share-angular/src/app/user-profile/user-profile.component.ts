import { Component, OnInit } from '@angular/core';
import { PaperService } from '../Service/paper.service';
import { PaperView } from '../model/paperView';
import { Router } from '@angular/router';
import { ReviewService } from '../Service/review.service';
import { ReviewView } from '../model/reviewView';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public userPapers: Array<PaperView>;
  public completedPapers: Array<PaperView>;
  public userReviews: Array<ReviewView>;
  public role: string;

  constructor(private paperService: PaperService, private router: Router, private reviewService: ReviewService) { }

  ngOnInit(): void {

    this.role = localStorage.getItem('role');
    this.getUserPapers();
    if (this.role === 'ROLE_USER') {
      this.getUserReviews();
    }
    if (this.role === 'ROLE_ADMIN') {
      this.getCompletedPapers();
    }
  }

  getUserPapers() {
    this.paperService.getUserPapers().subscribe(
      (response => {
        if (response !== null) {
          this.userPapers = response;
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  getUserReviews() {
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

  getCompletedPapers() {
    this.paperService.getCompletedPapers().subscribe(
      (response => {
        if (response !== null) {
          this.completedPapers = response;
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }
}
