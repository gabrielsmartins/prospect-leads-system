import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Product } from '../model/product.model';
import { ProductServiceMapper } from './product.service.mapper';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  create(product: Product) : Observable<Product> {
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
      'Accept' : 'application/json'
    });
    const url = environment.product_endpoint;
    return this.httpClient.post(url, product, {headers})
                          .pipe(map((data : any) =>  ProductServiceMapper.mapToModel(data)));
  }

  update(id: number, product: Product) : Observable<Product> {
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
      'Accept' : 'application/json'
    });
    let url = `${environment.product_endpoint}/${id}`;
    return this.httpClient.put(url, product, {headers})
                          .pipe(map((data : any) =>  ProductServiceMapper.mapToModel(data)));
  }

  findAll(): Observable<Product[]> {
    const headers = new HttpHeaders({
        'Content-Type':  'application/json',
        'Accept' : 'application/json'
    });
    return this.httpClient.get(environment.product_endpoint, {headers})
                          .pipe(map((data: any) => data.map((item: any) => ProductServiceMapper.mapToModel(data)) ));
  }

  findById(id: number) : Observable<Product | undefined> {
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
      'Accept' : 'application/json'
    });
    let url = `${environment.product_endpoint}/${id}`;
    return this.httpClient.get(url, {headers})
                          .pipe(map((data : any) => ProductServiceMapper.mapToModel(data)));
  }

}
