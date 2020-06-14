import { Component, OnInit } from '@angular/core';
import { PaperService } from '../Service/paper.service';
import { PaperView } from '../model/paperView';
import { MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-search-dialog',
  templateUrl: './search-dialog.component.html',
  styleUrls: ['./search-dialog.component.css']
})
export class SearchDialogComponent implements OnInit {

  dialog: boolean;
  foundPapers: Array<PaperView>;
  private searchForm: FormGroup;


  constructor( private paperService: PaperService, private snackBar: MatSnackBar, private formBuilder: FormBuilder) { }


  ngOnInit(): void {
    this.dialog = false;
  }

  openDialogue() {
    this.dialog = true;
  }

  closeQuestionDialog() {
    this.dialog = false;
  }

  search() {
    this.paperService.search(null).subscribe(
      (response => {
        if (response !== null) {
          this.foundPapers = response;
        }
      }),
      (error => {
        this.snackBar.open(error.error.message);
      })
    );
  }

  buildForm() {
    this.searchForm = this.formBuilder.group({
      title: ['', []],
      date: ['', []],
      language: ['', []],
      author: ['', []],
      keyword: ['', []],
      text: ['', []]
    });
  }

}
