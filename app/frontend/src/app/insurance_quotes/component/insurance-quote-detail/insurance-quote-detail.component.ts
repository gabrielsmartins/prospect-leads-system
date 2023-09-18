import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InsuranceQuoteService } from '../../service/insurance-quote.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SuccessDialogService } from 'src/app/shared/dialog/success-dialog/service/success-dialog.service';
import { map } from 'rxjs';
import { InsuranceQuote } from '../../model/insurance-quote.model';

@Component({
  selector: 'app-insurance-quote-detail',
  templateUrl: './insurance-quote-detail.component.html',
  styleUrls: ['./insurance-quote-detail.component.css']
})
export class InsuranceQuoteDetailComponent {

  form: FormGroup;
  insuranceQuote?: InsuranceQuote;

  constructor(private route: ActivatedRoute, private service: InsuranceQuoteService, private router: Router, private formBuilder: FormBuilder, private successDialogService: SuccessDialogService) { 
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
    const id = String(this.route.snapshot.paramMap.get('id'));
    this.service.findById(id)
                .subscribe(insuranceQuote => this.insuranceQuote = insuranceQuote);
  }

  onSubmit() : void {
    if (this.insuranceQuote) {
      this.insuranceQuote.finished = true;
      this.insuranceQuote.finished_at = new Date();
      this.service.update(this.insuranceQuote)
        .subscribe(() => {
          this.successDialogService.openDialog('Seguro contrato com sucesso', 200);
          this.router.navigate(["/insurance-quote-simulation"]);
        })
    }
  }
  
}
