import { Product } from "../model/product.model";

export class ProductServiceMapper {

    public static mapToModel(data : any) : Product {
        let product = new Product();
        product.id = data.id;
        product.name = data.name;
        product.category = data.category;
        product.active = data.active;
        product.minTotalMonthlyPremiumAmount = data.minTotalMonthlyPremiumAmount;
        product.maxTotalMonthlyPremiumAmount = data.maxTotalMonthlyPremiumAmount;
        product.suggestedTotalMonthlyPremiumAmount = data.suggestedTotalMonthlyPremiumAmount;
        product.totalCoverageAmount = data.totalCoverageAmount;
        product.coverages = data.coverages;
        product.createdAt = data.createdAt;
        product.updatedAt = data.updatedAt;
        product.deletedAt = data.deletedAt;
        return product;
    }

}