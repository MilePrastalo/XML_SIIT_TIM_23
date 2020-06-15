import { Component, OnInit } from '@angular/core';
import { PaperView } from '../model/paperView';
import { PaperService } from '../Service/paper.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  public loggedIn: boolean;
  papers: Array<PaperView>;

  constructor(private paperService: PaperService, private snackBar: MatSnackBar) { }


  ngOnInit(): void {
    this.loggedIn = false;
    if ( localStorage.getItem('role') !== '' ) {
      this.loggedIn = true;
    }
    this.allPublishedPapers();
  }

  showSearchResult($event) {
    this.papers = $event;
    JSON.stringify(this.papers);
  }

  allPublishedPapers() {
    this.paperService.getAllPublishedPapers().subscribe(
      (response => {
        if (response !== null) {
          this.papers = response;
        }
      }),
      (error => {
        this.snackBar.open(error.error.message);
      })
    );
  }

}
