import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AssignReview } from '../model/assignReview';
import { ReviewService } from '../Service/review.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-assign-review',
  templateUrl: './assign-review.component.html',
  styleUrls: ['./assign-review.component.css']
})
export class AssignReviewComponent implements OnInit {

  assignReviewForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private reviewService: ReviewService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.assignReviewForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      publicationName: ['', [Validators.required]]
    });
  }

  get username() { return this.assignReviewForm.controls.username.value as string; }
  get publicationName() { return this.assignReviewForm.controls.publicationName.value as string; }

  onAssignReviewSubmit() {
    const assignReview = new AssignReview(this.username, this.publicationName);

    this.reviewService.assignReview(assignReview).subscribe((response => {
      this.snackBar.open(response);
    }),
    (error => {
      const err = JSON.parse(error.error);
      this.snackBar.open(err.message);
    }));
  }

}
