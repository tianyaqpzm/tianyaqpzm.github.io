import { Component, OnInit } from '@angular/core';
import { RxjsCacheService } from 'src/app/service/cache/rxjs-cache.service';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})
export class DemoComponent implements OnInit {
  public cache: any;

  constructor(private cacheService: RxjsCacheService) {}

  ngOnInit(): void {
    this.cacheService.getCache$().subscribe({
      next: value => {
        this.cache = value;
        console.log('接收到值：', value);
      }
    });

    this.cacheService.getCustomerInfo('customerid').subscribe(
      value => {
        this.cacheService.getCache$().next(value);
      },
      error => {
        console.log(error);
      }
    );
  }
}
