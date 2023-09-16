import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LoadingDialogComponent } from './dialog/loading-dialog/component/loading-dialog.component';
import { ErrorDialogComponent } from './dialog/error-dialog/component/error-dialog.component';
import { LoadingDialogService } from './dialog/loading-dialog/service/loading-dialog.service';
import { ErrorDialogService } from './dialog/error-dialog/service/error-dialog.service';
import { MaterialModule } from './material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SideNavModule } from './side-nav/side-nav.module';
import { SuccessDialogComponent } from './dialog/success-dialog/component/success-dialog.component';


const sharedComponents = [LoadingDialogComponent, ErrorDialogComponent, SuccessDialogComponent];

@NgModule({
  declarations: [...sharedComponents],
  imports: [CommonModule, 
            RouterModule, 
            MaterialModule, 
            SideNavModule,
            FormsModule,
            ReactiveFormsModule],
  exports: [...sharedComponents, CommonModule, MaterialModule, SideNavModule, FormsModule, ReactiveFormsModule],
  providers: [ErrorDialogService, LoadingDialogService]
})
export class SharedModule {

}