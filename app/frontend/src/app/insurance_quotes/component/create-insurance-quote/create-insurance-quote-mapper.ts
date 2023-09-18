import { FormGroup } from "@angular/forms";
import { InsuranceQuote } from '../../model/insurance-quote.model';
import { Customer } from "../../model/customer.model";

export class CreateInsuranceQuoteMapper {

    public static mapToModel(form: FormGroup) : InsuranceQuote {
        let insuranceQuote = new InsuranceQuote();
        insuranceQuote.type = form.get('product')?.value.category;
        insuranceQuote.product_id = form.get('product')?.value.id;
        let customer = new Customer();
        customer.name = form.get('name')?.value;
        customer.document_number = form.get('document_number')?.value;
        customer.type = form.get('person_type')?.value;
        customer.gender = form.get('gender')?.value;
        customer.date_of_birth = form.get('date_of_birth')?.value;
        customer.email = form.get('e_mail')?.value;
        customer.phone_number = form.get('phone_number')?.value;
        insuranceQuote.customer = customer
        return insuranceQuote;
    }

}