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
  }

  accept(name: string) {
    this.reviewService.acceptReview(name).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.log(error);
      }
    );
  }
  reject(name: string) {
    this.reviewService.rejectReview(name).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.log(error);
      }
    );
  }

  edit(name: string) {
    this.router.navigateByUrl("/addReview" + name);
  }

}
