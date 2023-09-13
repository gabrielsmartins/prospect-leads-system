import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateInsuranceQuoteComponent } from './create-insurance-quote.component';

describe('CreateInsuranceQuoteComponent', () => {
  let component: CreateInsuranceQuoteComponent;
  let fixture: ComponentFixture<CreateInsuranceQuoteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateInsuranceQuoteComponent]
    });
    fixture = TestBed.createComponent(CreateInsuranceQuoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
