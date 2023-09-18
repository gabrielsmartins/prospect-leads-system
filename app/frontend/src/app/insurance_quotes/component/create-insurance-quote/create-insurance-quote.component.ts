import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InsuranceQuoteService } from '../../service/insurance-quote.service';
import { Router } from '@angular/router';
import { SuccessDialogService } from 'src/app/shared/dialog/success-dialog/service/success-dialog.service';
import { Product } from 'src/app/products/model/product.model';
import { InsuranceQuote } from '../../model/insurance-quote.model';
import { ProductService } from '../../../products/service/product.service';
import { filter, map } from 'rxjs';
import { CreateInsuranceQuoteMapper } from './create-insurance-quote-mapper';

@Component({
  selector: 'app-create-insurance-quote',
  templateUrl: './create-insurance-quote.component.html',
  styleUrls: ['./create-insurance-quote.component.css']
})
export class CreateInsuranceQuoteComponent implements OnInit {

  form: FormGroup;
  products: Array<Product> = new Array();

  constructor(private productService: ProductService, private insuranceQuoteService: InsuranceQuoteService, private router: Router, private formBuilder: FormBuilder) { 
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      product: ['', Validators.required],
      document_number: ['', Validators.required],
      person_type: ['', Validators.required],
      gender: ['', Validators.required],
      date_of_birth: ['', Validators.required],
      e_mail: ['', Validators.required],
      phone_number: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.productService.findAll()
                       .pipe(map((products : Product[]) => products.filter(product => product.active)))
                       .subscribe(products =>  {
                         this.products = products;
                        });
  }

  onSubmit() : void {
    let insuranceQuote = CreateInsuranceQuoteMapper.mapToModel(this.form);
    this.insuranceQuoteService.create(insuranceQuote)
                               .subscribe(createdInsuranceQuote => {
                                  let id = createdInsuranceQuote.id;
                                  this.router.navigate([`/insurance-quote-simulation/${id}`]);
                               }); 
  }
  
}
