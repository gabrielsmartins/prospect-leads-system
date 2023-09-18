import { Customer } from '../model/customer.model';
import { InsuranceQuote } from '../model/insurance-quote.model';

export class InsuranceQuoteServiceMapper {

    public static mapToModel(data: any) : InsuranceQuote {
        let insuranceQuote = new InsuranceQuote();
        insuranceQuote.id = data.id;
        insuranceQuote.type = data.type;
        insuranceQuote.product_id = data.product_id;
        insuranceQuote.total_monthly_premium_amount = data.total_monthly_premium_amount;
        insuranceQuote.total_coverage_amount = data.total_coverage_amount;
        insuranceQuote.coverages = data.coverages;
        insuranceQuote.assistances = data.assistances;
        insuranceQuote.finished = data.finished;
        let customer = new Customer();
        customer.document_number = data.customer.document_number;
        customer.name = data.customer.name;
        customer.type = data.customer.type;
        customer.gender = data.customer.gender;
        customer.date_of_birth = data.customer.date_of_birth;
        customer.email = data.customer.email;
        customer.phone_number = data.customer.phone_number;
        insuranceQuote.customer = customer;
        insuranceQuote.created_at = data.created_at;
        insuranceQuote.updated_at = data.updated_at;
        insuranceQuote.finished_at = data.finished_at;
        return insuranceQuote;
    }

}