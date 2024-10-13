import { Component } from '@angular/core';
import { HeroComponent } from '../../ui/components/hero/hero.component';
import { NgOptimizedImage } from '@angular/common';

@Component({
  standalone: true,
  imports: [HeroComponent, NgOptimizedImage],
  templateUrl: './home.component.html',
  styles: ``,
})
export class HomeComponent {}
