import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StubsService {
  name: string | undefined;
  isBusy: boolean | undefined;

  constructor() {}

  getValue() {
    if (this.isBusy) {
      return 'real value';
    } else {
      return 'fake value';
    }
  }
}
