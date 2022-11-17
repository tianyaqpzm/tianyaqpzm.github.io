import { Component, OnInit } from '@angular/core';
import { StubsService } from 'src/app/service/stubs.service';

@Component({
  selector: 'app-stubs',
  template: `
    <span>{{ message }}</span>
  `,
  styleUrls: ['./stubs.component.css']
})
export class StubsComponent implements OnInit {
  message: string | undefined;

  constructor(private stubs: StubsService) {}

  ngOnInit(): void {
    this.message = !this.stubs.isBusy
      ? this.stubs.name + ' 服务可用'
      : this.stubs.name + ' 服务正忙';
  }
}
