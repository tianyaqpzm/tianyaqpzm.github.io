import { createAction, props } from '@ngrx/store'

export const increment = createAction('increment', props<{ count: number }>())
export const decrement = createAction('decrement')

export const increment_async = createAction('increment_async')

// export declare function props<P extends object>(): Props<P>
