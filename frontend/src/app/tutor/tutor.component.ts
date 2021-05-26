import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router, ActivatedRoute } from '@angular/router';

import { Tutor } from "../sistema/dto/tutor";

import { CrudService } from "../sistema/crud.service";
import { SistemaService } from "../sistema/sistema.service";

@Component({
  selector: 'app-tutor',
  templateUrl: './tutor.component.html',
  styleUrls: ['./tutor.component.css']
})
export class TutorComponent implements OnInit {

  tutor: Tutor = new Tutor();
		
  constructor( private route : Router, private actRoute : ActivatedRoute, private crudService : CrudService, public sistemaService : SistemaService ) { }

  ngOnInit(): void {
	this.sistemaService.resetaMSGs();
		
	this.carregaTutor();	
  }
  
  carregaTutor() : void {
	let tutorId = this.actRoute.snapshot.paramMap.get( "tutorId" );

	this.crudService.getTutor( tutorId ).subscribe(
		dados => {
			this.tutor = dados;

			this.sistemaService.setMensagemOk( "Tutor carregado com êxito!" );			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  navegaParaAnimal() : void {
	let animalId = this.actRoute.snapshot.paramMap.get( "animalId" );
	
	this.sistemaService.redireciona( [ 'animal', animalId ] );
  }
  	  
}

