import { Customer } from "./customer.model";

export class InsuranceQuote {
    id?: string;
    type?: string;
    customer?: Customer;
    productId?: number;
    createdAt?: Date;
}
