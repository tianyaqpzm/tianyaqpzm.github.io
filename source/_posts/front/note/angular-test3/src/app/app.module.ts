import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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

@NgModule({
  declarations: [
    RootComponent,
    AppComponent,
    StubsComponent,
    SpyComponent,
    VideoPlayerComponent,
    DemoComponent,
    VideoOriginComponent,
    VideoTrackingComponent
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    }
  ],
  bootstrap: [RootComponent]
})
export class AppModule {}
