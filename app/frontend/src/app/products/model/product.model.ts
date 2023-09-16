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

    toJSON() : any {
        return {
            id: this.id,
            name: this.name,
            category : this.category as string,
            active : this.active,
            min_total_monthly_premium_amount : this.min_total_monthly_premium_amount,
            max_total_monthly_premium_amount : this.max_total_monthly_premium_amount,
            suggested_total_monthly_premium_amount : this.suggested_total_monthly_premium_amount,
            total_coverage_amount : this.total_coverage_amount,
            coverages: Object.fromEntries(this.coverages),
            assistances : this.assistances,
            created_at : this.created_at,
            updated_at : this.updated_at,
            deleted_at : this.deleted_at
        }
    }

}
