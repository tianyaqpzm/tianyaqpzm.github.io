import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RxjsCacheService {
  private cachehavior$ = new BehaviorSubject({ a: 1 });

  constructor(private http: HttpClient) {}

  getCustomerInfo(customerid: string): Observable<any> {
    return this.http.get<any>('http://localhost:3005/api/customerinfo');
  }
  getCache$(): Subject<any> {
    return this.cachehavior$;
  }
}
