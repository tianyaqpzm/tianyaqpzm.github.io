import { createFeatureSelector, createSelector } from '@ngrx/store'
import { AppState } from '..'
import { adapter, State, todoFeatureKey } from '../reducers/todo.reducer'

export const selectTodo = createFeatureSelector<AppState, State>(todoFeatureKey)
// export const selectTodos = createSelector(selectTodo, (state) => state.todos)

const {
  selectIds,
  selectEntities,
  selectAll,
  selectTotal,
} = adapter.getSelectors()

export const selectTodos = createSelector(selectTodo, selectAll)
