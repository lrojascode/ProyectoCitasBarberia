import { Component } from '@angular/core';
import { NgOptimizedImage } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-profesionals',
  standalone: true,
  imports: [NgOptimizedImage, RouterLink],
  templateUrl: './profesionals.component.html',
  styles: ``,
})
export class ProfesionalsComponent {}
