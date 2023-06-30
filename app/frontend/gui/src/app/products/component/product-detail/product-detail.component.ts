import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { ProductService } from '../../service/product.service';
import { OnInit } from '@angular/core';
import { Product } from '../../model/product.model';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private service: ProductService,
              private location: Location) {}

  product: Product | undefined;    
  
  productForm: Product = {
    id : undefined,
    name: '',
    active: true,
    category: undefined,
    createdAt: new Date(),
    updatedAt: undefined,
    deletedAt: undefined,
  };

  ngOnInit(): void {
    this.findById();
  }

  findById() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.findById(id)
                 .subscribe(product => this.product = product);
  }

  goBack() : void {
    this.location.back();
  }

  update() : void {
    this.productForm.updatedAt = new Date();
  }

  delete() : void {
    this.productForm.deletedAt = new Date();
  }

}
