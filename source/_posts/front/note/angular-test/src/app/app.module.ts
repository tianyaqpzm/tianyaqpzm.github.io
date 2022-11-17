import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { AppComponent } from './app.component'
import { HomeComponent } from './pages/home/home.component'
import { AboutComponent } from './pages/about/about.component'
import { NewsComponent } from './pages/news/news.component'
import { RouterModule, Routes } from '@angular/router'
import { DemoModule } from './demo/demo.module'
import { DemoComponent } from './demo/demo.component'
import { StoreModule } from '@ngrx/store'
import { reducers, metaReducers } from './store'
import { StoreDevtoolsModule } from '@ngrx/store-devtools'
import { environment } from '../environments/environment'
import { EffectsModule } from '@ngrx/effects'
import { CounterEffects } from './store/effects/counter.effects'
import { TodoComponent } from './demo/todo/todo.component'

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full',
    data: {
      animation: 'one',
    },
  },
  {
    path: 'about',
    component: AboutComponent,
    data: {
      animation: 'two',
    },
  },
  {
    path: 'news',
    component: NewsComponent,
    data: {
      animation: 'three',
    },
  },
  {
    path: 'demo',
    component: DemoComponent,
    data: {
      animation: 'three',
    },
  },
  {
    path: 'todo',
    component: TodoComponent,
    data: {
      animation: 'three',
    },
  },
]

@NgModule({
  declarations: [AppComponent, HomeComponent, AboutComponent, NewsComponent],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    DemoModule,
    StoreModule.forRoot(reducers, { metaReducers }),
    !environment.production ? StoreDevtoolsModule.instrument() : [],
    EffectsModule.forRoot([CounterEffects]),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
