import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../../services/usuario.service'; // Importa el servicio

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css'],
  standalone: true // Hacer que el componente sea standalone
})
export class PerfilComponent implements OnInit {

  perfil: any = {}; // Donde se almacenarán los datos del perfil

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    // Llamada al servicio para obtener los datos del usuario
    const userId = 1; // Cambiar esto al ID del usuario actual
    this.usuarioService.getPerfilUsuario(userId).subscribe(data => {
      this.perfil = data.customerProfile;
    }, error => {
      console.log('Error al obtener perfil del usuario', error);
    });
  }
}

