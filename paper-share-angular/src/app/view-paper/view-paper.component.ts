import { Component, OnInit, Input } from '@angular/core';
import { PaperService } from '../Service/paper.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view-paper',
  templateUrl: './view-paper.component.html',
  styleUrls: ['./view-paper.component.css']
})
export class ViewPaperComponent implements OnInit {

  @Input() name: string;
  public html: string;
  constructor(private paperService: PaperService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.name = this.route.snapshot.paramMap.get('name');
    this.getPaper();
  }

  getPaper() {
    this.paperService.getPaper(this.name).subscribe(
      (response => {
        if (!response) {
          alert('File not exist or is empty');
          this.router.navigateByUrl('');
        }
        this.html = response;
      }),
      (error => {
        console.log(JSON.stringify(error));
        alert(error.error.message);
      })
    );
  }

}
