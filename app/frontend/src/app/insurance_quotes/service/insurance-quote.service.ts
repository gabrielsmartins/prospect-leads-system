import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InsuranceQuote } from '../model/insurance-quote.model';
import { Observable } from 'rxjs';
const crypto = require('crypto');

@Injectable({
  providedIn: 'root'
})
export class InsuranceQuoteService {

  constructor(private httpClient: HttpClient) { }

  create(insuranceQuote: InsuranceQuote) : Observable<InsuranceQuote> {
    insuranceQuote.id = crypto.randomUUID();
    insuranceQuote.createdAt = new Date();
    return new Observable(subscriber => {
      subscriber.next(insuranceQuote);
      subscriber.complete();
    });
  }

}
