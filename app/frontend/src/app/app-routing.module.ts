import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateProductComponent } from './products/component/create-product/create-product.component';
import { RouterModule, Routes } from '@angular/router';
import { SearchProductComponent } from './products/component/search-product/search-product.component';
import { ProductDetailComponent } from './products/component/product-detail/product-detail.component';
import { CreateInsuranceQuoteComponent } from './insurance_quotes/component/create-insurance-quote/create-insurance-quote.component';
import { InsuranceQuoteDetailComponent } from './insurance_quotes/component/insurance-quote-detail/insurance-quote-detail.component';

const routes: Routes = [
  { path: 'create-product', component: CreateProductComponent},
  { path: 'products', component: SearchProductComponent},
  { path: 'products/:id', component: ProductDetailComponent},
  { path: 'create-insurance-quote-simulation', component: CreateInsuranceQuoteComponent},
  { path: 'insurance-quote-simulation/:id', component: InsuranceQuoteDetailComponent},
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


