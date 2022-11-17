import { Action, createReducer, on } from '@ngrx/store'

import { addTodo, deleteTodo } from '../actions/todo.actions'
import { v4 as uuidv4 } from 'uuid'
import { createEntityAdapter, EntityAdapter, EntityState } from '@ngrx/entity'
import { act } from '@ngrx/effects'
export const todoFeatureKey = 'todo'

export interface Todo {
  id: string
  title: string
}

/*
	{
		ids: [1, 2],
		entities: {
			1: { id: 1, title: "Hello Angular" },
			2: { id: 2, title: "Hello NgRx" }
		}
	}
*/
export interface State extends EntityState<Todo> {}

export const adapter: EntityAdapter<Todo> = createEntityAdapter<Todo>()
export const initialState: State = adapter.getInitialState()

export const reducer = createReducer(
  initialState,
  on(addTodo, (state, action) =>
    adapter.addOne({ id: uuidv4(), title: action.title }, state)
  ),
  on(deleteTodo, (state, action) => adapter.removeOne(action.id, state))
)
