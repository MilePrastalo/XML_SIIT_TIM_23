import { Component, OnInit } from '@angular/core';
import { XonomyService } from '../Service/xonomy.service';
import { PaperService } from '../Service/paper.service';
import { PaperUpload } from '../model/paperUpload';
import { Router, ActivatedRoute } from '@angular/router';

declare const Xonomy: any;
@Component({
  selector: 'app-create-paper',
  templateUrl: './create-paper.component.html',
  styleUrls: ['./create-paper.component.css']
})
export class CreatePaperComponent implements OnInit {

  constructor(private xonomyService: XonomyService, private paperService: PaperService, private router: Router,
    private route: ActivatedRoute) { }
  scientificPublication = '';
  fileToUpload: File = null;
  fileString: any;
  paperName: string;
  coverLetter:string;
  ngOnInit(): void {
    this.paperName = this.route.snapshot.paramMap.get('title');
    console.log(this.paperName);
  }

  ngAfterViewInit() {
    this.scientificPublication = '<ScientificPaper xmlns="https://github.com/MilePrastalo/XML_SIIT_TIM_23"' + ' xmlns:pred="https://github.com/MilePrastalo/XML_SIIT_TIM_23/predicate/"></ScientificPaper>';
    if (this.paperName) {
      this.paperService.getPaperAsText(this.paperName).subscribe(
        response => { 
          this.scientificPublication = response;
          Xonomy.render(this.scientificPublication, xonomyElement, this.xonomyService.scientificPublicationSpecification);
         }
      );
      this.paperService.getCoverLetter(this.paperName).subscribe(
        response => { 
          this.coverLetter = response;
          (document.getElementById("cover") as HTMLTextAreaElement).value = response;
         }
      );
    }
    let xonomyElement = document.getElementById("editor");
    Xonomy.render(this.scientificPublication, xonomyElement, this.xonomyService.scientificPublicationSpecification);
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
      Xonomy.render(this.fileString, xonomyElement, this.xonomyService.scientificPublicationSpecification);
    };
  }
  sendFile() {
    let text = Xonomy.harvest();
    let cover = (document.getElementById("cover") as HTMLTextAreaElement).value;
    this.paperService.sendPaper(new PaperUpload(text, cover)).subscribe(
      response => {
        console.log("Hello");
        this.router.navigateByUrl('/user-profile');
      },
      error => {
        console.log(error);
      }
    );
  }
  back() {
    this.router.navigateByUrl('/user-profile');
  }

  updateFile(){
    let text = Xonomy.harvest();
    let cover = (document.getElementById("cover") as HTMLTextAreaElement).value;
    this.paperService.updatePaper(new PaperUpload(text, cover), this.paperName).subscribe(
      response => {
        console.log("Hello");
        this.router.navigateByUrl('/user-profile');
      },
      error => {
        console.log(error);
      }
    );
  }

}
