import { Injectable } from '@angular/core';
import { DataService } from './data.service';
import { StubsService } from './stubs.service';

@Injectable({
  providedIn: 'root'
})
export class MasterService {
  constructor(private valueService: StubsService) {}
  getValue() {
    return this.valueService.getValue();
  }
}
