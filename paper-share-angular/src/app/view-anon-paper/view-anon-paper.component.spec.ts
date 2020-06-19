import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAnonPaperComponent } from './view-anon-paper.component';

describe('ViewAnonPaperComponent', () => {
  let component: ViewAnonPaperComponent;
  let fixture: ComponentFixture<ViewAnonPaperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAnonPaperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAnonPaperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
