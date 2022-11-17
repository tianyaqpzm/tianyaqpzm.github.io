import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PanelComponent } from './panel/panel.component';
import { DemoComponent } from './demo.component';
import { NgrxComponent } from './ngrx/ngrx.component';
import { TodoComponent } from './todo/todo.component';



@NgModule({
  declarations: [PanelComponent, DemoComponent, NgrxComponent, TodoComponent],
  imports: [
    CommonModule
  ]
})
export class DemoModule { }
