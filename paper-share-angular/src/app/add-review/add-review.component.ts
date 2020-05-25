import { Component, OnInit } from '@angular/core';
import { XonomyService } from '../Service/xonomy.service';
import { PaperUpload } from '../model/paperUpload';
import { ReviewService } from '../Service/review.service';
declare const Xonomy: any;
@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {
  scientificPublication = '';
  fileToUpload: File = null;
  fileString: any;
  paperTitle: string;
  constructor(private xonomyService: XonomyService, private reviewService: ReviewService) { }

  ngOnInit(): void {
    if (!this.paperTitle) {
      this.paperTitle = 'Naslov';
    }
  }

  ngAfterViewInit() {
    this.scientificPublication = '<Review><Reviewer>' + this.getUsername() +
      '</Reviewer><ReviewPaper><paperTitle>' + this.paperTitle +
      '</paperTitle><paperAuthorUsername>Unknown</paperAuthorUsername></ReviewPaper></Review>';
    let xonomyElement = document.getElementById("editor");
    Xonomy.render(this.scientificPublication, xonomyElement, this.xonomyService.reviewSpecification);
  }
  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);
    var reader = new FileReader();
    reader.readAsText(this.fileToUpload);
    reader.onloadend = (e) => {
      console.log(reader.result);
      this.fileString = reader.result;
      let xonomyElement = document.getElementById("editor");
      Xonomy.render(this.fileString, xonomyElement, this.xonomyService.reviewSpecification);
    };
  }
  sendFile() {
    let text = Xonomy.harvest();
    this.reviewService.sendReview(new PaperUpload(text, '')).subscribe(
      response => {
        console.log("Hello");
      }
    );
  }

  getUsername(): string {
    return "Pera"
  }


}
