import { NgModule } from "@angular/core";
import { CreateInsuranceQuoteComponent } from "./component/create-insurance-quote/create-insurance-quote.component";
import { SearchInsuranceQuoteComponent } from "./component/search-insurance-quote/search-insurance-quote.component";
import { SharedModule } from "../shared/shared.module";
import { AppRoutingModule } from "../app-routing.module";
import { InsuranceQuoteService } from "./service/insurance-quote.service";
import { InsuranceQuoteDetailComponent } from './component/insurance-quote-detail/insurance-quote-detail.component';

@NgModule({
    declarations : [CreateInsuranceQuoteComponent, SearchInsuranceQuoteComponent, InsuranceQuoteDetailComponent],
    imports : [AppRoutingModule, SharedModule],
    providers : [InsuranceQuoteService]
})
export class InsuranceQuoteModule {

}