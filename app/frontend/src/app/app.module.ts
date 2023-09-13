import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import { MatListModule } from '@angular/material/list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatPaginatorModule} from '@angular/material/paginator';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddProductComponent } from './products/component/add-product/add-product.component';
import { AppRoutingModule } from './app-routing.module';
import { SearchProductComponent } from './products/component/search-product/search-product.component';
import { ProductDetailComponent } from './products/component/product-detail/product-detail.component';
import { SideNavComponent } from './shared/side-nav/side-nav.component';
import { CreateInsuranceQuoteComponent } from './insurance_quotes/component/create-insurance-quote/create-insurance-quote.component';
import { SearchInsuranceQuoteComponent } from './insurance_quotes/component/search-insurance-quote/search-insurance-quote.component';

@NgModule({
  declarations: [
    AppComponent,
    AddProductComponent,
    SearchProductComponent,
    ProductDetailComponent,
    SideNavComponent,
    CreateInsuranceQuoteComponent,
    SearchInsuranceQuoteComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MatSidenavModule,
    MatMenuModule,
    MatListModule,
    MatExpansionModule,
    MatTooltipModule,
    MatToolbarModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatPaginatorModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
