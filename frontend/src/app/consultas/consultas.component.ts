import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router, ActivatedRoute } from '@angular/router';

import { Animal } from "../sistema/dto/animal";
import { Veterinario } from "../sistema/dto/veterinario";

import { Consulta } from "../sistema/dto/consulta";
import { ConsultaCad } from "../sistema/dto/consulta-cad";
import { ConsultaFiltro} from "../sistema/dto/consulta-filtro";

import { CrudService } from "../sistema/crud.service";
import { SistemaService } from "../sistema/sistema.service";
import { Const } from "../const";

@Component({
  selector: 'app-consultas',
  templateUrl: './consultas.component.html',
  styleUrls: ['./consultas.component.css']
})
export class ConsultasComponent implements OnInit {

  consultas: Consulta[] = new Array();
  consultaCad: ConsultaCad = new ConsultaCad();
  consultaFiltro: ConsultaFiltro = new ConsultaFiltro();

  statuss: string[] = new Array();
  
  veterinarios: Veterinario[] = new Array();
  animais: Animal[] = new Array();
	
  constructor( private route : Router, private actRoute : ActivatedRoute, private crudService : CrudService, public sistemaService : SistemaService ) { }

  ngOnInit(): void {
	this.sistemaService.resetaMSGs();
	
	this.carregaStatuss();
	this.carregaAnimais();
	this.carregaVeterinarios();
	
	this.consultaFiltro.dataConsulta = "*";
	this.consultaFiltro.status = "*";
	
	this.filtraConsultas();	
	
	this.consultaFiltro.status = "DEFAULT";
	this.consultaCad.animalId = -1;
	this.consultaCad.veterinarioId = -1;
  }
    
  registraConsulta() {	  
 	this.sistemaService.resetaMSGs();
	
	if ( this.consultaCad.animalId == -1 ) {
		this.sistemaService.setMensagemErro( "Selecione um animal." );
		return;
	}
	
	if ( this.consultaCad.veterinarioId == -1 ) {
		this.sistemaService.setMensagemErro( "Selecione um veterinário" );
		return;
	}
 				
	this.crudService.registraConsulta( this.consultaCad ).subscribe(
		response => {
			this.resetaFormRegistro();
			this.filtraConsultas();
			this.sistemaService.setMensagemOk( "Consulta cadastrada." );
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }    
  	
  filtraConsultas() : void {
	this.sistemaService.resetaMSGs();
	
	if ( this.consultaFiltro.status == "DEFAULT" )
		this.consultaFiltro.status = "*";
				
	this.crudService.filtraConsultas( this.consultaFiltro ).subscribe(
		dados => {
			this.consultas = dados;
			
			this.sistemaService.setMensagemOk( "Consultas filtradas com êxito!" );
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  visualiza( id : any ) : void {
	this.sistemaService.redireciona( [ 'consulta', id ] );
  }
    
  deleta( id : any ): void {
	this.sistemaService.resetaMSGs();

	this.crudService.deletaConsulta( id ).subscribe(
		dados => {
			this.filtraConsultas();
			this.sistemaService.setMensagemOk( "Consulta removida!" );			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  
  carregaStatuss() : void {
	this.crudService.listaStatuss().subscribe(
		data => {
			this.statuss = data;
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	); 
  }
  
  carregaVeterinarios() : void {
	this.crudService.filtraVeterinarios( "*" ).subscribe(
		data => {
			this.veterinarios = data;			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  carregaAnimais() : void {
	this.crudService.filtraAnimais( "*" ).subscribe(
		data => {
			this.animais = data;
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  resetaFormRegistro() : void {
	this.consultaCad.dataConsulta = "";
	this.consultaCad.status = "";
	this.consultaCad.veterinarioId = 0;
	this.consultaCad.animalId = 0;
  }

}
