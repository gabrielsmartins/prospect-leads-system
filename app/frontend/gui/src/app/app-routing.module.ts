import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddProductComponent } from './products/component/add-product/add-product.component';
import { RouterModule, Routes } from '@angular/router';
import { SearchProductComponent } from './products/component/search-product/search-product.component';
import { ProductDetailComponent } from './products/component/product-detail/product-detail.component';

const routes: Routes = [
  { path: 'new-product', component: AddProductComponent},
  { path: 'products', component: SearchProductComponent},
  { path: 'products/:id', component: ProductDetailComponent},
  { path: '', redirectTo: '/products', pathMatch: 'full' },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }


