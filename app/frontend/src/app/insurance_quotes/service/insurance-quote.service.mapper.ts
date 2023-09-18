import { InsuranceQuote } from "../model/insurance-quote.model";

export class InsuranceQuoteServiceMapper {

    public static mapToModel(data: any) : InsuranceQuote {
        return new InsuranceQuote();
    }

}