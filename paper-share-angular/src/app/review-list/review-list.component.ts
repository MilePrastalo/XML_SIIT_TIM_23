import { Component, OnInit, Input } from '@angular/core';
import { ReviewView } from '../model/reviewView';
import { ReviewService } from '../Service/review.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {

  @Input() reviews: Array<ReviewView>;
  public role: string;
  public accepted: boolean;
  constructor(private reviewService: ReviewService, private router: Router) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
  }

  accept(name: string) {
    this.reviewService.acceptReview(name).subscribe(
      response => {
        for (let review of this.reviews) {
          if (review.reviewName === name) {
            review.status = 'accepted';
          }
        }
      },
      error => {
        if (error.status == 200) {
          for (let review of this.reviews) {
            if (review.reviewName === name) {
              review.status = 'accepted';
            }
          }
        }
      }
    );
  }
  reject(name: string) {
    this.reviewService.rejectReview(name).subscribe(
      response => {
        for (let i = 0; i < this.reviews.length; i++) {
          if (this.reviews[i].reviewName === name) {
            this.reviews.splice(i, 1);
            break;
          }
        }
      },
      error => {
        if (error.status == 200) {
          for (let i = 0; i < this.reviews.length; i++) {
            if (this.reviews[i].reviewName === name) {
              this.reviews.splice(i, 1);
              break;
            }
          }
        }
      }
    );
  }

  edit(name: string) {
    this.router.navigateByUrl("/addReview/" + name);
  }
  publish(name: string) {
    this.reviewService.publishReview(name).subscribe(
      response => {
        for (let review of this.reviews) {
          if (review.reviewName === name) {
            review.status = 'submitted';
          }
        }
      }
    );
  }

  sendReviewsToAuthor(paperName: string) {
    this.reviewService.sendReviewsToAuthor(paperName).subscribe(
      (response => {
        console.log('Done!');
        this.getSubmittedReviews();
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  getSubmittedReviews() {
    this.reviewService.getSubmittedReviews().subscribe(
      (response => {
        console.log(response);
        if (response !== null) {
          this.reviews = response;
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  viewReview(name: string) {
    // this.router.navigate(['/review', name]);
    window.open('/review/' + name);
  }

}
