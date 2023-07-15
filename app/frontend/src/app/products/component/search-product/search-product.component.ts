import { Component, ViewChild, OnInit } from '@angular/core';
import { Product } from '../../model/product.model';
import { ProductService } from '../../service/product.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-search-product',
  templateUrl: './search-product.component.html',
  styleUrls: ['./search-product.component.css']
})
export class SearchProductComponent implements OnInit {

  constructor(private service: ProductService) {}

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  products: Product[] = [];
  displayedColumns: string[] = ['id', 'name',  'category', 'active', 'createdAt', 'updatedAt', 'deletedAt', 'detail'];
  pageSize = 10;
  totalItems = this.products.length;
  displayedProducts: Product[] = [];

  ngOnInit(): void {
    this.findAll();
  }

  private findAll() {
    this.service.findAll()
                .subscribe(data => {
                  this.products = data;
                })  
  }
  
  onPageChange(event: PageEvent): void {
    const startIndex = event.pageIndex * event.pageSize;
    const endIndex = startIndex + event.pageSize;
    this.displayedProducts = this.products.slice(startIndex, endIndex);
  }

}
