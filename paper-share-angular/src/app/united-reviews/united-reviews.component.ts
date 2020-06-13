import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../Service/review.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-united-reviews',
  templateUrl: './united-reviews.component.html',
  styleUrls: ['./united-reviews.component.css']
})
export class UnitedReviewsComponent implements OnInit {

  name: string;
  public html: string;
  constructor(private reviewService: ReviewService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log('Here!');
    this.route.params.subscribe(params => {
      this.name = params['name'];
    });
    this.getReview();
  }

  getReview() {
    this.reviewService.getUnitedReview(this.name).subscribe(
      (response => {
        this.html = response;
      }),
      (error => {
        console.log(JSON.stringify(error));
        alert(error.error.message);
      })
    );
  }
}
