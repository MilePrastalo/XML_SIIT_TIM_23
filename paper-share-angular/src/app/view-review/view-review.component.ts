import { Component, OnInit, Input } from '@angular/core';
import { ReviewService } from '../Service/review.service';

@Component({
  selector: 'app-view-review',
  templateUrl: './view-review.component.html',
  styleUrls: ['./view-review.component.css']
})
export class ViewReviewComponent implements OnInit {

  @Input() name: string;
  public html: string;
  constructor(private reviewService: ReviewService) { }

  ngOnInit(): void {
    this.name = 'rev2.xml';
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
