import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router, ActivatedRoute } from '@angular/router';

import { Veterinario } from "../sistema/dto/veterinario";

import { CrudService } from "../sistema/crud.service";
import { SistemaService } from "../sistema/sistema.service";

@Component({
  selector: 'app-veterinario',
  templateUrl: './veterinario.component.html',
  styleUrls: ['./veterinario.component.css']
})
export class VeterinarioComponent implements OnInit {

  veterinario: Veterinario = new Veterinario();
		
  constructor( private route : Router, private actRoute : ActivatedRoute, private crudService : CrudService, public sistemaService : SistemaService ) { }

  ngOnInit(): void {
	this.sistemaService.resetaMSGs();
		
	this.carregaVeterinario();	
  }
  
  carregaVeterinario() : void {
	let veterinarioId = this.actRoute.snapshot.paramMap.get( "veterinarioId" );

	this.crudService.getVeterinario( veterinarioId ).subscribe(
		dados => {
			this.veterinario = dados;

			this.sistemaService.setMensagemOk( "Veterinario carregado com êxito!" );			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
}


