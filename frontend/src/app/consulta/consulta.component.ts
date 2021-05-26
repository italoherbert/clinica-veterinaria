import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router, ActivatedRoute } from '@angular/router';

import { Consulta } from "../sistema/dto/consulta";

import { CrudService } from "../sistema/crud.service";
import { SistemaService } from "../sistema/sistema.service";

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {

  consulta: Consulta = new Consulta();
		
  statuss: string[] = new Array();
		
  constructor( private route : Router, private actRoute : ActivatedRoute, private crudService : CrudService, public sistemaService : SistemaService ) { }

  ngOnInit(): void {
	this.sistemaService.resetaMSGs();
		
	this.carregaStatuss();
	this.carregaConsulta();	
  }
  
  carregaConsulta() : void {
	let consultaId = this.actRoute.snapshot.paramMap.get( "consultaId" );

	this.crudService.getConsulta( consultaId ).subscribe(
		dados => {
			this.consulta = dados;

			this.sistemaService.setMensagemOk( "Consulta carregado com êxito!" );			
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
  	
  salvaStatus() : void {
	let consultaId = this.actRoute.snapshot.paramMap.get( "consultaId" );

	this.crudService.salvaStatus( consultaId, this.consulta.status ).subscribe(
		dados => {
			this.sistemaService.setMensagemOk( "Status salvo com êxito!" );			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
	
}
