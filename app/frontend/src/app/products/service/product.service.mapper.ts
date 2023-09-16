import { Product } from "../model/product.model";

export class ProductServiceMapper {

    public static mapToModel(data : any) : Product {
        let product = new Product();
        product.id = data.id;
        product.name = data.name;
        product.category = data.category;
        product.active = data.active;
        product.min_total_monthly_premium_amount = data.min_total_monthly_premium_amount;
        product.max_total_monthly_premium_amount = data.max_total_monthly_premium_amount;
        product.suggested_total_monthly_premium_amount = data.suggested_total_monthly_premium_amount;
        product.total_coverage_amount = data.total_coverage_amount;
        product.coverages = data.coverages;
        product.created_at = data.created_at;
        product.updated_at = data.updated_at;
        product.deleted_at = data.deleted_at;
        return product;
    }

}