import { Injectable } from '@angular/core';
import { delay, Observable, of } from 'rxjs';

const books = [
  '《图书 壹》',
  '《图书 贰》',
  '《图书 叁》',
  '《图书 肆》',
  '《图书 伍》'
];

@Injectable({
  providedIn: 'root'
})
export class AsyncDemoService {
  constructor() {}

  getData(): Observable<string[]> {
    return of(books).pipe(delay(500));
  }

  setData(name: string) {
    return [...books, name];
  }
}
