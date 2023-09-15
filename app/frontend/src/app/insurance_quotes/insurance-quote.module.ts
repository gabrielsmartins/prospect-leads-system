import { NgModule } from "@angular/core";
import { CreateInsuranceQuoteComponent } from "./component/create-insurance-quote/create-insurance-quote.component";
import { SearchInsuranceQuoteComponent } from "./component/search-insurance-quote/search-insurance-quote.component";
import { SharedModule } from "../shared/shared.module";

@NgModule({
    declarations : [CreateInsuranceQuoteComponent, SearchInsuranceQuoteComponent],
    imports: [SharedModule]
})
export class InsuranceQuoteModule {

}