import { Component, OnInit, Input } from '@angular/core';
import { ReviewService } from '../Service/review.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-review',
  templateUrl: './view-review.component.html',
  styleUrls: ['./view-review.component.css']
})
export class ViewReviewComponent implements OnInit {

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
    this.reviewService.getReview(this.name).subscribe(
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
