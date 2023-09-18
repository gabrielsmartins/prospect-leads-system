import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandler, Injectable, NgZone } from '@angular/core';
import { ErrorDialogService } from 'src/app/shared/dialog/error-dialog/service/error-dialog.service';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(
    private errorDialogService: ErrorDialogService,
    private zone: NgZone
  ) {}

  handleError(error: any) {
    console.error('Error from global error handler', error);
    if (!(error instanceof HttpErrorResponse)) {
      error = error.rejection;
    }
    this.zone.run(() =>
      this.errorDialogService.openDialog(
        error?.message || 'Undefined client error',
        error?.status
      )
    );
    if (error != undefined && error != null) {
      console.error('Error from global error handler', error);
    }
  }
}