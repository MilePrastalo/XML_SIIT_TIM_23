import { Component, OnInit } from '@angular/core';
import { PaperService } from '../Service/paper.service';
import { PaperView } from '../model/paperView';
import { Router } from '@angular/router';

@Component({
  selector: 'app-paper-list',
  templateUrl: './paper-list.component.html',
  styleUrls: ['./paper-list.component.css']
})
export class PaperListComponent implements OnInit {

  public papers: Array<PaperView>;

  constructor(private paperService: PaperService, private router: Router) { }

  ngOnInit(): void {
    this.getUserPapers();
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

  openPaper(name: string) {
    this.router.navigate(['/view-paper', name]);
  }

}
