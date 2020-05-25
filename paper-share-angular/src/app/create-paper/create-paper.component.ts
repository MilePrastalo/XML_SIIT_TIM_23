import { Component, OnInit } from '@angular/core';
import { XonomyService } from '../Service/xonomy.service';
import { PaperService } from '../Service/paper.service';
import { PaperUpload } from '../model/paperUpload';

declare const Xonomy: any;
@Component({
  selector: 'app-create-paper',
  templateUrl: './create-paper.component.html',
  styleUrls: ['./create-paper.component.css']
})
export class CreatePaperComponent implements OnInit {

  constructor(private xonomyService: XonomyService, private paperService: PaperService) { }
  scientificPublication = '';
  fileToUpload: File = null;
  fileString: any;
  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.scientificPublication = '<ScientificPaper></ScientificPaper>';
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
    this.paperService.sendPaper(new PaperUpload(text,cover)).subscribe(
      response => {
        console.log("Hello");
      }
    );
  }

}
