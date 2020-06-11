import { Component, OnInit, Input } from '@angular/core';
import { ReviewService } from '../Service/review.service';
import { ReviewView } from '../model/reviewView';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-paper-reviews',
  templateUrl: './paper-reviews.component.html',
  styleUrls: ['./paper-reviews.component.css']
})
export class PaperReviewsComponent implements OnInit {

  public paperName: string;
  public paperReviews: Array<ReviewView>;

  constructor(private reviewService: ReviewService, private route: ActivatedRoute) { }

  ngOnInit(): void {
      this.paperName = this.route.snapshot.paramMap.get('paperName');
      this.getUserReviews();
  }

  getUserReviews() {
    this.reviewService.getPaperReviews(this.paperName).subscribe(
      (response => {
        if (response !== null) {
          this.paperReviews = response;
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

}
