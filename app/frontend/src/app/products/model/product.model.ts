import { Category } from "./category.model";

export class Product {

    id?: number;
    name?: string;
    category?: Category;
    active: boolean = true;
    min_total_monthly_premium_amount?: number;
    max_total_monthly_premium_amount?: number;
    suggested_total_monthly_premium_amount?: number;
    total_coverage_amount?: number;
    coverages: Map<string, number> = new Map();
    assistances: Array<string> = new Array();
    created_at?: Date;
    updated_at?: Date;
    deleted_at?: Date;

}
