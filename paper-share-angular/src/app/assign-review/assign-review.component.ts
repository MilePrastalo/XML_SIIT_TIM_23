import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AssignReview } from '../model/assignReview';
import { ReviewService } from '../Service/review.service';
import { MatSnackBar } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-assign-review',
  templateUrl: './assign-review.component.html',
  styleUrls: ['./assign-review.component.css']
})
export class AssignReviewComponent implements OnInit {

  assignReviewForm: FormGroup;
  title: string;
  recommendedReviewers: string[];
  user: string;

  constructor(private reviewService: ReviewService, private snackBar: MatSnackBar,
              private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.title = params['title'];
    });

    this.getRecommendedReviewers(this.title);
  }

  onAssignReviewSubmit() {

    if (this.user == null || this.user === '') {
      this.snackBar.open('You must choose a reviewer.');
      return;
    }

    const assignReview = new AssignReview(this.user, this.title);

    this.reviewService.assignReview(assignReview).subscribe((response => {
      this.snackBar.open(response);
    }),
    (error => {
      const err = JSON.parse(error.error);
      this.snackBar.open(err.message);
    }));
  }

  getRecommendedReviewers(title: string) {
    this.reviewService.getRecommendedReviewers(title).subscribe((response => {
      this.recommendedReviewers = response;
    }),
    (error => {
      const err = JSON.parse(error.error);
      this.snackBar.open(err.message);
    }));
  }

  back() {
    this.router.navigateByUrl('/user-profile' + name);
  }

}
