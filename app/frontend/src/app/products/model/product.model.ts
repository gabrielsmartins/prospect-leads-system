import { Category } from "./category.model";

export class Product {

    id?: number;
    name?: string;
    category?: Category;
    active: boolean = true;
    minTotalMonthlyPremiumAmount?: number;
    maxTotalMonthlyPremiumAmount?: number;
    suggestedTotalMonthlyPremiumAmount?: number;
    totalCoverageAmount?: number;
    coverages?: Map<string, number>;
    assistances?: Array<string>;
    createdAt?: Date;
    updatedAt?: Date;
    deletedAt?: Date;

}
