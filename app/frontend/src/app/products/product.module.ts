import { NgModule } from "@angular/core";
import { CreateProductComponent } from "./component/create-product/create-product.component";
import { ProductService } from "./service/product.service";
import { SearchProductComponent } from "./component/search-product/search-product.component";
import { ProductDetailComponent } from "./component/product-detail/product-detail.component";
import { SharedModule } from "../shared/shared.module";
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
    declarations : [CreateProductComponent, 
                    SearchProductComponent, 
                    ProductDetailComponent],
    imports : [AppRoutingModule, SharedModule],
    providers : [ProductService]
})
export class ProductModule {

}