import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-dialog',
  templateUrl: './search-dialog.component.html',
  styleUrls: ['./search-dialog.component.css']
})
export class SearchDialogComponent implements OnInit {

  constructor() { }
  dialog = false;
  ngOnInit(): void {
  }

  openDialogue() {
    this.dialog = true;
  }
  closeQuestionDialog() {
    this.dialog = false;

  }
  search() {

  }

}
