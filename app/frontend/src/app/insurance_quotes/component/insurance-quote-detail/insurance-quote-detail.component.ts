import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InsuranceQuoteService } from '../../service/insurance-quote.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SuccessDialogService } from 'src/app/shared/dialog/success-dialog/service/success-dialog.service';
import { map } from 'rxjs';
import { InsuranceQuote } from '../../model/insurance-quote.model';
import { Product } from 'src/app/products/model/product.model';
import { ProductService } from 'src/app/products/service/product.service';

@Component({
  selector: 'app-insurance-quote-detail',
  templateUrl: './insurance-quote-detail.component.html',
  styleUrls: ['./insurance-quote-detail.component.css']
})
export class InsuranceQuoteDetailComponent {

  form: FormGroup;
  insuranceQuote?: InsuranceQuote;
  products: Array<Product> = new Array();
  selectedProduct?: Product;

  constructor(private route: ActivatedRoute, private productService: ProductService, private insuranceQuoteService: InsuranceQuoteService, private router: Router, private formBuilder: FormBuilder, private successDialogService: SuccessDialogService) {
    this.form = this.formBuilder.group({
      id: [{ value: '', disabled: true }, Validators.required],
      name: [{ value: '', disabled: true }, Validators.required],
      product: [{ value: this.selectedProduct, disabled: true }, Validators.required],
      document_number: [{ value: '', disabled: true }, Validators.required],
      person_type: [{ value: '', disabled: true }, Validators.required],
      gender: [{ value: '', disabled: true }, Validators.required],
      date_of_birth: [{ value: '', disabled: true }, Validators.required],
      e_mail: [{ value: '', disabled: true }, Validators.required],
      phone_number: [{ value: '', disabled: true }, Validators.required]
    });
  }

  ngOnInit(): void {
    const id = String(this.route.snapshot.paramMap.get('id'));
    this.productService.findAll()
                       .pipe(map((products : Product[]) => products.filter(product => product.active)))
                       .subscribe(products =>  {
                          this.products = products;
                       });
    this.insuranceQuoteService.findById(id)
                              .subscribe(insuranceQuote => {
                                this.insuranceQuote = insuranceQuote;
                                if (insuranceQuote.product_id) {
                                  this.productService.findById(insuranceQuote.product_id)
                                      .subscribe(product => this.selectedProduct = product)
                                }
                              });
  }

  onSubmit() : void {
    if (this.insuranceQuote) {
      this.insuranceQuote.finished = true;
      this.insuranceQuote.finished_at = new Date();
      this.insuranceQuoteService.update(this.insuranceQuote)
        .subscribe(() => {
          this.successDialogService.openDialog('Seguro contrato com sucesso', 200);
          this.router.navigate(["/create-insurance-quote-simulation"]);
        })
    }
  }

}
