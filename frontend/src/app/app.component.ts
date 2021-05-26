import { Component } from '@angular/core';

import { SistemaService } from './sistema/sistema.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  title = 'Sistema de clínica veterinária';
  
  constructor( public sistemaService : SistemaService ) {}
  	  
}
