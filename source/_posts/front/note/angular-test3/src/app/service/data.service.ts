import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private http: HttpClient) {}

  getBooks(): Observable<string[]> {
    return this.http.get<string[]>('api/books');
  }

  addBook(name: string) {
    return this.http.post<string>('api/books', { book: name });
  }
}
