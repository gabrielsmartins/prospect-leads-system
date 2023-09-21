import { CustomerType } from "./customer-type.model";
import { CustomerGender } from './customer-gender.model';

export class Customer {
    document_number?: string;
    name?: string;
    type?: CustomerType;
    gender?: CustomerGender;
    date_of_birth?: Date;
    email?: string;
    phone_number?: number;
}
