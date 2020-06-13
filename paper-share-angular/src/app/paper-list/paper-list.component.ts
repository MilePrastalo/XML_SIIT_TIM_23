import { Component, OnInit, Input } from '@angular/core';
import { PaperService } from '../Service/paper.service';
import { PaperView } from '../model/paperView';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-paper-list',
  templateUrl: './paper-list.component.html',
  styleUrls: ['./paper-list.component.css']
})
export class PaperListComponent implements OnInit {

  @Input() papers: Array<PaperView>;
  @Input() forUser: boolean;

  constructor(private paperService: PaperService, private router: Router,  private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  getUserPapers() {
    this.paperService.getUserPapers().subscribe(
      (response => {
        if (response !== null) {
          this.papers = response;
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  acceptPaper(paperName: string, index: number) {
    this.paperService.acceptPaper(paperName).subscribe(
      (response => {
        if (response !== null) {
          this.snackBar.open('Accepted!');
          this.papers.splice(0, index);
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  rejectPaper(paperName: string, index: number) {
    this.paperService.rejectPaper(paperName).subscribe(
      (response => {
        if (response !== null) {
          this.snackBar.open('Rejected!');
          this.papers.splice(0, index);
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  assignReview() {
    this.router.navigateByUrl('/assign-review');
  }

  openPaper(name: string) {
    this.router.navigate(['/view-paper', name]);
  }

  goToPaperReviews( paperName: string) {
    this.router.navigate(['/paper-reviews', paperName]);
  }

  deletePaper(title: string, index: number) {
    this.paperService.deletePaper(title).subscribe(
      (response => {
        this.snackBar.open('Deleted paper: ' + title);
        this.papers.splice(index, 1);
      }),
      (error => {
        const err = JSON.parse(error.error);
        this.snackBar.open(err.message);
      })
    );

  }
  addPaper(){
    this.router.navigateByUrl('add');
  }
  edit(name: string) {
    this.router.navigateByUrl('add/' + name);
  }

}
