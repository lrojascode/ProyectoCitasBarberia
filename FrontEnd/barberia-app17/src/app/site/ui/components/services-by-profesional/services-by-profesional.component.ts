import { Component, Input, numberAttribute } from '@angular/core';

@Component({
  selector: 'app-services-by-profesional',
  standalone: true,
  imports: [],
  templateUrl: './services-by-profesional.component.html',
  styles: ``,
})
export class ServicesByProfesionalComponent {
  @Input({ transform: numberAttribute }) id = 0;
}
