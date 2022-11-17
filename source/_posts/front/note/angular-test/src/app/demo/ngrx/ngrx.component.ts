import { Component, OnInit } from '@angular/core'
import { select, Store } from '@ngrx/store'
import { Observable } from 'rxjs'
import { AppState } from 'src/app/store'
import { decrement, increment } from 'src/app/store/actions/counter.actions'
import { selectCount } from 'src/app/store/selectors/counter.selectors'

@Component({
  selector: 'app-ngrx',
  templateUrl: './ngrx.component.html',
  styles: [],
})
export class NgrxComponent implements OnInit {
  count: Observable<number>

  constructor(private store: Store<AppState>) {
    this.count = this.store.pipe(select(selectCount))
  }
  ngOnInit(): void {}

  increment() {
    this.store.dispatch(increment({ count: 5 }))
  }
  decrement() {
    this.store.dispatch(decrement())
  }
  increment_async() {
    this.store.dispatch(increment_async())
  }
}
