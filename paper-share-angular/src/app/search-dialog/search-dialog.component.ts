import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { PaperService } from '../Service/paper.service';
import { PaperView } from '../model/paperView';
import { MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SearchDto } from '../model/searchDto';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-search-dialog',
  templateUrl: './search-dialog.component.html',
  styleUrls: ['./search-dialog.component.css'],
  providers: [DatePipe]
})
export class SearchDialogComponent implements OnInit {

  dialog: boolean;
  foundPapers: Array<PaperView>;
  searchForm: FormGroup;
  keywords: Array<string> = new Array<string>();
  @Output() searchResult = new EventEmitter< Array<PaperView> >();
  @Input() forUser: boolean;


  constructor( private paperService: PaperService, private snackBar: MatSnackBar, private formBuilder: FormBuilder,
               private datePipe: DatePipe) { }


  ngOnInit(): void {
    this.dialog = false;
  }

  openDialogue() {
    this.buildForm();
    this.dialog = true;
  }

  closeQuestionDialog() {
    this.dialog = false;
  }

  search() {
    const dto: SearchDto = this.getSearchData();
    this.paperService.search(dto).subscribe(
      (response => {
        if (response !== null) {
          this.foundPapers = response;
          this.searchResult.emit(response);
        }
      }),
      (error => {
        this.snackBar.open(error.error.message);
      })
    );
  }

  basicSearch() {
    alert('to do');
  }

  getSearchData() {
    const dto: SearchDto = new SearchDto();
    dto.title = this.searchForm.controls.title.value;
    dto.language = this.searchForm.controls.language.value;
    dto.date = this.searchForm.controls.date.value;
    if ( dto.date !== '') {
      dto.date = this.datePipe.transform(dto.date, 'dd-MM-yyyy');
    }
    dto.authors = this.searchForm.controls.author.value;
    dto.forUser = this.forUser;
    dto.keywords = this.searchForm.controls.keyword.value;
    return dto;
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
