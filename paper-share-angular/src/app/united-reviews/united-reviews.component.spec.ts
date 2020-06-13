import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnitedReviewsComponent } from './united-reviews.component';

describe('UnitedReviewsComponent', () => {
  let component: UnitedReviewsComponent;
  let fixture: ComponentFixture<UnitedReviewsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnitedReviewsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnitedReviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
