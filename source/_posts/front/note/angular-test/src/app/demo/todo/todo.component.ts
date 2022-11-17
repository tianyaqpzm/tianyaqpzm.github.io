import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core'
import { select, Store } from '@ngrx/store'
import { title } from 'process'
import { fromEvent, Observable } from 'rxjs'
import { filter, map } from 'rxjs/operators'
import { AppState } from 'src/app/store'
import { addTodo, deleteTodo } from 'src/app/store/actions/todo.actions'
import { Todo } from 'src/app/store/reducers/todo.reducer'
import { selectTodo, selectTodos } from 'src/app/store/selectors/todo.selectors'

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styles: [],
})
export class TodoComponent implements AfterViewInit {
  @ViewChild('AddTodoInput')
  AddTodoInput!: ElementRef

  todos: Observable<Todo[]>

  constructor(private store: Store<AppState>) {
    this.todos = this.store.pipe(select(selectTodos))
  }

  ngOnInit(): void {}

  ngAfterViewInit(): void {
    // 按下Enter后自动添加到列表
    fromEvent<KeyboardEvent>(this.AddTodoInput.nativeElement, 'keyup')
      .pipe(
        filter((event) => event.key === 'Enter'),
        map((event) => (<HTMLInputElement>event.target).value),
        map((title) => title.trim()),
        filter((title) => title !== '')
      )
      .subscribe((title) => {
        this.store.dispatch(addTodo({ title }))
        this.AddTodoInput.nativeElement.value = ''
      })
  }
  deleteTodo(id: string) {
    this.store.dispatch(deleteTodo({ id }))
  }
}
