import { Component, OnInit, Input } from '@angular/core';
import { PaperService } from '../Service/paper.service';

@Component({
  selector: 'app-view-paper',
  templateUrl: './view-paper.component.html',
  styleUrls: ['./view-paper.component.css']
})
export class ViewPaperComponent implements OnInit {

  @Input() name: string;
  public html: string;
  constructor(private paperService: PaperService) { }

  ngOnInit(): void {
    this.name = 'sciExample1';
    this.getPaper();
  }

  getPaper() {
    this.paperService.getPaper(this.name).subscribe(
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
