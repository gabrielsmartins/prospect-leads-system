import { NgModule } from "@angular/core";
import { MaterialModule } from "../material/material.module";
import { SideNavComponent } from "./side-nav.component";
import { RouterModule } from '@angular/router';

@NgModule({
    declarations : [SideNavComponent],
    imports : [RouterModule, MaterialModule],
    exports: [SideNavComponent]
})
export class SideNavModule {

}