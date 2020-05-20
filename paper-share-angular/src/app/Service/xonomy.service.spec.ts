import { TestBed } from '@angular/core/testing';

import { XonomyService } from './xonomy.service';

describe('XonomyService', () => {
  let service: XonomyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(XonomyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
