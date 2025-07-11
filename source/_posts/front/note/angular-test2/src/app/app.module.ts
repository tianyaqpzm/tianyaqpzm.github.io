import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StubsComponent } from './component/stubs/stubs.component';
import { SpyComponent } from './component/spy/spy.component';
import { VideoPlayerComponent } from './component/video-player/video-player.component';
import { DemoComponent } from './business/demo/demo.component';
import { RootComponent } from './root.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { VideoOriginComponent } from './component/video-origin/video-origin.component';
import { HttpClientModule } from '@angular/common/http';
import { VideoTrackingComponent } from './component/video-tracking/video-tracking.component';
// 引入组件
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { TrackingFaceCameraComponent } from './component/tracking-face-camera/tracking-face-camera.component';

// 模板中使用BasicChecked
@NgModule({
  declarations: [
    RootComponent,
    AppComponent,
    StubsComponent,
    SpyComponent,
    VideoPlayerComponent,
    DemoComponent,
    VideoOriginComponent,
    VideoTrackingComponent,
    TrackingFaceCameraComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule, MatButtonModule, MatRadioModule],
  providers: [
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy,
    },
  ],
  bootstrap: [RootComponent],
})
export class AppModule {}
