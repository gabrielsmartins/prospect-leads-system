import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Router } from '@angular/router';
import { Product } from '../../model/product.model';
import { Category } from '../../model/category.model';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit{

  constructor(private service: ProductService, private router: Router) {}

  productForm: Product = {
    name: '',
    active: true,
    category: undefined,
    createdAt: new Date()
  };

  categories = Object.values(Category);

  ngOnInit(): void {}

  create() {
    this.service.create(this.productForm)
                 .subscribe(() => {
                  this.router.navigate(["/"]);
                 })
  }

}
