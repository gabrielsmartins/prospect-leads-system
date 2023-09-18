import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InsuranceQuote } from '../model/insurance-quote.model';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { InsuranceQuoteServiceMapper } from './insurance-quote.service.mapper';

@Injectable({
  providedIn: 'root'
})
export class InsuranceQuoteService {

  constructor(private httpClient: HttpClient) { }

  create(insuranceQuote: InsuranceQuote) : Observable<InsuranceQuote> {
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
      'Accept' : 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': '*',
      'Access-Control-Allow-Methods': '*'
    });
    const url = environment.insurance_quote_endpoint;
    return this.httpClient.post(url, insuranceQuote, {headers})
                          .pipe(map((data : any) =>  InsuranceQuoteServiceMapper.mapToModel(data)));
  }

}
