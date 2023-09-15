import { FormControl, FormGroup } from "@angular/forms";
import { Product } from "../../model/product.model"

export class AddProductMapper {

    public static mapToModel(form: FormGroup) : Product{
        let product = new Product();
        product.name = form.get('name')?.value;
        product.active = true;
        product.category = form.get('category')?.value;
        product.maxTotalMonthlyPremiumAmount = form.get('max_total_monthly_premium_amount')?.value;
        product.minTotalMonthlyPremiumAmount = form.get('min_total_monthly_premium_amount')?.value;
        product.suggestedTotalMonthlyPremiumAmount = form.get('suggested_total_monthly_premium_amount')?.value;
        return product;
    }

}