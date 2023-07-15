import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../model/product.model';
import { Category } from '../model/category.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  create(product: Product) : Observable<Product> {
    product.id = Math.floor(Math.random() * 101);
    return new Observable(subscriber => {
      subscriber.next(product);
      subscriber.complete();
    });
  }

  findAll(): Observable<Product[]> {
    let productOne = new Product();
    productOne.id = 1;
    productOne.name = "Seguro Vida Individual";
    productOne.category = Category.LIFE;
    productOne.createdAt = new Date();
    productOne.updatedAt = new Date();

    let productTwo = new Product();
    productTwo.id = 2;
    productTwo.name = "Seguro Vida Familiar";
    productTwo.category = Category.LIFE;
    productTwo.createdAt = new Date();
    productTwo.updatedAt = new Date();
  
    return new Observable<Product[]>(subscriber => {
      subscriber.next([productOne, productTwo]);
      subscriber.complete();
    });
  }

  findById(id: number) : Observable<Product | undefined> {
    let product = new Product();
    product.id = id;
    product.name = "Seguro Vida Individual";
    product.category = Category.LIFE;
    product.createdAt = new Date();
    product.updatedAt = new Date();

    return new Observable<Product>(subscriber => {
      subscriber.next(product);
      subscriber.complete();
    }); 
  }

}
