import { Component, OnInit, Input } from '@angular/core';
import { XonomyService } from '../Service/xonomy.service';
import { ReviewService } from '../Service/review.service';
import { SendReview } from '../model/SendReview';
import { Router, ActivatedRoute } from '@angular/router';
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
  reviewName: string;
  reviewText: string;
  constructor(private xonomyService: XonomyService, private reviewService: ReviewService, private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.reviewName = this.route.snapshot.paramMap.get('title');
  }

  ngAfterViewInit() {
    this.reviewService.getReviewAsText(this.reviewName).subscribe(
      (response: string) => {
        this.scientificPublication = response;
        let xonomyElement = document.getElementById("editor");
        Xonomy.render(this.scientificPublication, xonomyElement, this.xonomyService.reviewSpecification);
      }
    )

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
    this.reviewService.sendReview(new SendReview(text, this.reviewName)).subscribe(
      response => {
        console.log("Hello");
      },
      error=>{
        if(error.status === 200){
          this.router.navigateByUrl("/user-profile");
        }
      }
    );
  }
}
