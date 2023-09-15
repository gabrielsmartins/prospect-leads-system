import { NgModule } from "@angular/core";
import { MaterialModule } from "../shared/material/material.module";
import { AddProductComponent } from "./component/add-product/add-product.component";
import { ProductService } from "./service/product.service";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SearchProductComponent } from "./component/search-product/search-product.component";
import { ProductDetailComponent } from "./component/product-detail/product-detail.component";
import { SharedModule } from "../shared/shared.module";
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
    declarations : [AddProductComponent, 
                    SearchProductComponent, 
                    ProductDetailComponent],
    imports : [AppRoutingModule, SharedModule],
    providers : [ProductService]
})
export class ProductModule {

}