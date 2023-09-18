import { NgModule } from "@angular/core";
import { CreateInsuranceQuoteComponent } from "./component/create-insurance-quote/create-insurance-quote.component";
import { SearchInsuranceQuoteComponent } from "./component/search-insurance-quote/search-insurance-quote.component";
import { SharedModule } from "../shared/shared.module";
import { AppRoutingModule } from "../app-routing.module";
import { InsuranceQuoteService } from "./service/insurance-quote.service";

@NgModule({
    declarations : [CreateInsuranceQuoteComponent, SearchInsuranceQuoteComponent],
    imports : [AppRoutingModule, SharedModule],
    providers : [InsuranceQuoteService]
})
export class InsuranceQuoteModule {

}