  import { Component, OnInit } from '@angular/core';
  import { ProductService } from '../../service/product.service';
  import { Router } from '@angular/router';
  import { Category } from '../../model/category.model';
  import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
  import { AddProductMapper } from './add-product-mapper';

  @Component({
    selector: 'app-add-product',
    templateUrl: './add-product.component.html',
    styleUrls: ['./add-product.component.css']
  })
  export class AddProductComponent implements OnInit{

    form: FormGroup;
    categories = Object.values(Category);

    constructor(private service: ProductService, private router: Router, private formBuilder: FormBuilder) { 
      this.form = this.formBuilder.group({
        name: ['', Validators.required],
        category: ['', Validators.required],
        max_total_monthly_premium_amount: ['', Validators.required],
        min_total_monthly_premium_amount: ['', Validators.required],
        suggested_total_monthly_premium_amount: ['', Validators.required],
        coverages: this.formBuilder.array([new FormControl()]),
        assistances: this.formBuilder.array([new FormControl()])
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
  
    addCoverage(): void {
      this.coverages.push(new FormControl());
    }
  
    removeCoverage(index: number): void {
      this.coverages.removeAt(index);
    }

    addAssistance(): void {
      this.assistances.push(new FormControl());
    }
  
    removeAssistance(index: number): void {
      this.assistances.removeAt(index);
    }

    onSubmit() {
      let product = AddProductMapper.mapToModel(this.form);
      this.service.create(product)
                  .subscribe(() => {
                    this.router.navigate(["/products"]);
                  })
    }

  }
