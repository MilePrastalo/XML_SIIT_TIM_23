import { Component, OnInit } from '@angular/core';
import { PaperService } from '../Service/paper.service';
import { PaperView } from '../model/paperView';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public userPapers: Array<PaperView>;
  public completedPapers: Array<PaperView>;
  public role: string;

  constructor(private paperService: PaperService, private router: Router) { }

  ngOnInit(): void {

    this.role = localStorage.getItem('role');
    this.getUserPapers();
  }

  getUserPapers() {
    this.paperService.getUserPapers().subscribe(
      (response => {
        if (response !== null) {
          this.userPapers = response;
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

}
