import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchInsuranceQuoteComponent } from './search-insurance-quote.component';

describe('SearchInsuranceQuoteComponent', () => {
  let component: SearchInsuranceQuoteComponent;
  let fixture: ComponentFixture<SearchInsuranceQuoteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchInsuranceQuoteComponent]
    });
    fixture = TestBed.createComponent(SearchInsuranceQuoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
