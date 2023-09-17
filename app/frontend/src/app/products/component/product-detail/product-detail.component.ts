import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { ProductService } from '../../service/product.service';
import { OnInit } from '@angular/core';
import { Product } from '../../model/product.model';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from '../../model/category.model';
import { SuccessDialogService } from 'src/app/shared/dialog/success-dialog/service/success-dialog.service';
import { ProductDetailMapper } from './product-detail-mapper';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  form: FormGroup;
  product: Product | undefined;
  categories = Object.values(Category);

  constructor(private route: ActivatedRoute, private service: ProductService, private location: Location, private router: Router, private formBuilder: FormBuilder, private successDialogService: SuccessDialogService) {
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
    this.findById();
  }

  private fillAssistances(): void {
    const productAssistances = this.product?.assistances || [];

    while (this.assistances.length) {
      this.assistances.removeAt(0);
    }

    for (const assistance of productAssistances) {
      this.addAssistance();
      const lastIndex = this.assistances.length - 1;
      this.assistances.at(lastIndex).patchValue({ assistance });
    }
  }

  private fillCoverages(): void {
    console.log(this.product);
    const coverageFormArray = this.product?.coverages ? this.mapCoveragesToFormArray(this.product.coverages) : [];
    this.coverages.clear();
    coverageFormArray.forEach((coverageGroup) => {
      this.coverages.push(coverageGroup);
    });
  }

  private mapCoveragesToFormArray(coverages: Map<string, number>): FormGroup[] {
    const coverageFormArray: FormGroup[] = [];
    for (const [key, value] of Object.entries(coverages)) {
      const coverageGroup = this.formBuilder.group({
        key: [key, Validators.required],
        value: [value, Validators.required],
      });
      coverageFormArray.push(coverageGroup);
    }
    return coverageFormArray;
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

  public newAssitance(): FormGroup {
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

  private findById() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.findById(id)
      .subscribe(product => {
        console.log(product);
        this.product = product;
        this.fillAssistances();
        this.fillCoverages();
      });
  }

  goBack(): void {
    this.location.back();
  }

  update(): void {
    let product = ProductDetailMapper.mapToModel(this.form);
    this.service.update(product)
      .subscribe(() => {
        this.successDialogService.openDialog('Produto alterado com sucesso', 204);
        this.router.navigate(["/products"]);
      })
  }

  delete(): void {
    let product = ProductDetailMapper.mapToModel(this.form);
    this.service.create(product)
      .subscribe(() => {
        this.successDialogService.openDialog('Produto excluído com sucesso', 204);
        this.router.navigate(["/products"]);
      })
  }

}
