import { Customer } from "./customer.model";

export class InsuranceQuote {
    id?: string;
    type?: string;
    customer?: Customer;
    product_id?: number;
    total_monthly_premium_amount? : number;
    total_coverage_amount? : number;
    coverages?: Map<string, number>;
    assistances?: Array<string>;
    finished?: boolean;
    created_at?: Date;
    updated_at?: Date;
    finished_at?: Date;
}
