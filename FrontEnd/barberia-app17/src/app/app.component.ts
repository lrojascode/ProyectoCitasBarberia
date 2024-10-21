import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PrimeNGConfig } from 'primeng/api';
import {
  ToastRailComponent
} from "./shared/components/ui/toast-rail/toast-rail.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ToastRailComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  title = 'barberia-app17';
  constructor(private primengConfig: PrimeNGConfig) {}

  ngOnInit(): void {
    this.primengConfig.ripple = true;
  }
}
