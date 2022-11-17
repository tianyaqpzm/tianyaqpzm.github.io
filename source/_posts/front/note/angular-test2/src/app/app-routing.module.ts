import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { DemoComponent } from './business/demo/demo.component';
import { VideoOriginComponent } from './component/video-origin/video-origin.component';
import { VideoPlayerComponent } from './component/video-player/video-player.component';
import { VideoTrackingComponent } from './component/video-tracking/video-tracking.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    pathMatch: 'full',
    data: {
      animation: 'one'
    }
  },
  {
    // demo
    path: 'demo',
    component: DemoComponent,
    data: {
      animation: 'three'
    }
  },
  {
    // video-js
    path: 'video',
    component: VideoPlayerComponent,
    data: {
      animation: 'three'
    }
  },
  {
    // 原生video
    path: 'video1',
    component: VideoOriginComponent,
    data: {
      animation: 'three'
    }
  },
  {
    // video-js
    path: 'video2',
    component: VideoTrackingComponent,
    data: {
      animation: 'three'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
