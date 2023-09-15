import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LoadingDialogComponent } from './loading/loading-dialog/component/loading-dialog.component';
import { ErrorDialogComponent } from './errors/error-dialog/component/error-dialog.component';
import { LoadingDialogService } from './loading/loading-dialog/service/loading-dialog.service';
import { ErrorDialogService } from './errors/error-dialog/service/error-dialog.service';
import { MaterialModule } from './material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SideNavModule } from './side-nav/side-nav.module';

const sharedComponents = [LoadingDialogComponent, ErrorDialogComponent];

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