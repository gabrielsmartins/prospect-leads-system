import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceQuoteDetailComponent } from './insurance-quote-detail.component';

describe('InsuranceQuoteDetailComponent', () => {
  let component: InsuranceQuoteDetailComponent;
  let fixture: ComponentFixture<InsuranceQuoteDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InsuranceQuoteDetailComponent]
    });
    fixture = TestBed.createComponent(InsuranceQuoteDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
