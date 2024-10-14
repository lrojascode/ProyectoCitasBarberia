import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NgOptimizedImage } from '@angular/common';

@Component({
  selector: 'site-header',
  standalone: true,
  imports: [RouterLink, NgOptimizedImage, RouterLinkActive],
  templateUrl: './site-header.component.html',
  styles: ``,
})
export class SiteHeaderComponent {}
