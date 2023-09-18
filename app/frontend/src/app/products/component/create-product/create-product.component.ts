import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Router } from '@angular/router';
import { Category } from '../../model/category.model';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CreateProductMapper } from './create-product-mapper';
import { SuccessDialogService } from '../../../shared/dialog/success-dialog/service/success-dialog.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  form: FormGroup;
  categories = Object.values(Category);

  constructor(private service: ProductService, private router: Router, private formBuilder: FormBuilder, private successDialogService: SuccessDialogService) {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      max_total_monthly_premium_amount: ['', Validators.required],
      min_total_monthly_premium_amount: ['', Validators.required],
      suggested_total_monthly_premium_amount: ['', Validators.required],
      coverages: this.formBuilder.array([this.newCoverage()]),
      assistances: this.formBuilder.array([this.newAssitance()])
    });
  }

  ngOnInit(): void {

  }

  get coverages(): FormArray {
    return this.form.get('coverages') as FormArray;
  }

  get assistances(): FormArray {
    return this.form.get('assistances') as FormArray;
  }

  private newCoverage(): FormGroup {
    return this.formBuilder.group({
      key: ['', Validators.required],
      value: ['', Validators.required]
    });
  }

  public addCoverage(): void {
    this.coverages.push(this.newCoverage());
  }

  public removeCoverage(index: number): void {
    this.coverages.removeAt(index);
  }

  private newAssitance(): FormGroup {
    return this.formBuilder.group({
      assistance: ['', Validators.required]
    });
  }

  public addAssistance(): void {
    this.assistances.push(this.newAssitance());
  }

  public removeAssistance(index: number): void {
    this.assistances.removeAt(index);
  }

  public onSubmit() {
    let product = CreateProductMapper.mapToModel(this.form);
    this.service.create(product)
      .subscribe(() => {
        this.successDialogService.openDialog('Produto criado com sucesso', 201);
        this.router.navigate(["/products"]);
      })
  }

}
