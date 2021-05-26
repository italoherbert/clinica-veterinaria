import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router, ActivatedRoute } from '@angular/router';

import { Veterinario } from "../sistema/dto/veterinario";
import { VeterinarioCad } from "../sistema/dto/veterinario-cad";

import { CrudService } from "../sistema/crud.service";
import { SistemaService } from "../sistema/sistema.service";
import { Const } from "../const";

@Component({
  selector: 'app-veterinarios',
  templateUrl: './veterinarios.component.html',
  styleUrls: ['./veterinarios.component.css']
})
export class VeterinariosComponent implements OnInit {

  veterinarios: Veterinario[] = new Array();
  veterinarioCad: VeterinarioCad = new VeterinarioCad();
    
  nomeIni: string = "*";
	
  constructor( private route : Router, private actRoute : ActivatedRoute, private crudService : CrudService, public sistemaService : SistemaService ) { }

  ngOnInit(): void {
	this.sistemaService.resetaMSGs();
	
	this.filtraVeterinarios();	
  }
  
  registraVeterinario() {	  
 	this.sistemaService.resetaMSGs();
 				
	this.crudService.registraVeterinario( this.veterinarioCad ).subscribe(
		response => {
			this.resetaFormRegistro();
			this.filtraVeterinarios();
			this.sistemaService.setMensagemOk( "Veterinario cadastrado." );
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }    
  	
  filtraVeterinarios() : void {
	this.sistemaService.resetaMSGs();
	
	if ( this.nomeIni.length == 0 ) {
		this.sistemaService.setMensagemErro( "As iniciais do nome do veterinário é um campo requerido." );	
		return;
	}

	this.crudService.filtraVeterinarios( this.nomeIni ).subscribe(
		dados => {
			this.veterinarios = dados;
			
			this.sistemaService.setMensagemOk( "Veterinários filtrados com êxito!" );
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  visualiza( id : any ) : void {
	 this.sistemaService.redireciona( [ 'veterinario', id ] );
  }
    
  deleta( id : any ): void {
	this.sistemaService.resetaMSGs();

	this.crudService.deletaVeterinario( id ).subscribe(
		dados => {
			this.filtraVeterinarios();
			this.sistemaService.setMensagemOk( "Veterinario removido!" );			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  resetaFormRegistro() : void {
	this.veterinarioCad.pessoa.nome = "";
	this.veterinarioCad.pessoa.telefone = "";
	this.veterinarioCad.pessoa.email = "";
  }

}

