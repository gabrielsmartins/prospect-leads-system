import { FormGroup } from "@angular/forms";
import { InsuranceQuote } from '../../model/insurance-quote.model';
import { Customer } from "../../model/customer.model";
import * as moment from "moment";

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
        let dateOfBirth = form.get('date_of_birth')?.value;
        const inputDate = moment(dateOfBirth._i, 'DD/MM/YYYY');
        customer.date_of_birth = inputDate.format('YYYY-MM-DD');
        customer.email = form.get('e_mail')?.value;
        customer.phone_number = form.get('phone_number')?.value;
        insuranceQuote.customer = customer
        return insuranceQuote;
    }

}