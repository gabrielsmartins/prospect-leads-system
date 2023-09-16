import { FormArray, FormGroup } from "@angular/forms";
import { Product } from "../../model/product.model"

export class AddProductMapper {

    public static mapToModel(form: FormGroup) : Product{
        let product = new Product();
        product.name = form.get('name')?.value;
        product.active = true;
        product.category = form.get('category')?.value;
        product.max_total_monthly_premium_amount = form.get('max_total_monthly_premium_amount')?.value;
        product.min_total_monthly_premium_amount = form.get('min_total_monthly_premium_amount')?.value;
        product.suggested_total_monthly_premium_amount = form.get('suggested_total_monthly_premium_amount')?.value;
        const coverages = form.get('coverages') as FormArray;
        coverages.controls.forEach((control) => {
            const key = control.value['key'] as string;
            const value = control.value['value'] as number;
            product.coverages.set(key, value);
        })
        const assistances = form.get('assistances') as FormArray;
        product.assistances = assistances.controls.map((control) => control.value['assistance']);
        return product;
    }

}