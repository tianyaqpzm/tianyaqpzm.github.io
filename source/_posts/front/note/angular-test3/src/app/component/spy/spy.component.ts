import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-spy',
  template: '{{caption}}',
  styleUrls: ['./spy.component.css']
})
export class SpyComponent implements OnInit {
  caption: string | undefined;

  isOpen = {
    status: false
  };

  constructor(private title: Title) {}

  ngOnInit(): void {
    this.title.setTitle('Angular Spying');
    this.caption = this.title.getTitle();
  }

  testField() {
    if (this.isOpen.status) {
      return 'open';
    } else {
      return 'close';
    }
  }
}
