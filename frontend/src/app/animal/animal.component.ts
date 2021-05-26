import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router, ActivatedRoute } from '@angular/router';

import { Animal } from "../sistema/dto/animal";
import { TutorCad } from "../sistema/dto/tutor-cad";

import { CrudService } from "../sistema/crud.service";
import { SistemaService } from "../sistema/sistema.service";
import { Const } from "../const";

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.css']
})
export class AnimalComponent implements OnInit {

  animal: Animal = new Animal();
  tutorCad: TutorCad = new TutorCad();  
	
  tutorNomeIni: string = "*";
	
  constructor( private route : Router, private actRoute : ActivatedRoute, private crudService : CrudService, public sistemaService : SistemaService ) { }

  ngOnInit(): void {
	this.sistemaService.resetaMSGs();
		
	this.carregaAnimal();	
  }
  
  carregaAnimal() : void {
	let animalId = this.actRoute.snapshot.paramMap.get( "animalId" );

	this.crudService.getAnimal( animalId ).subscribe(
		dados => {
			this.animal = dados;

			this.sistemaService.setMensagemOk( "Animal carregado com êxito!" );			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  	
  filtraTutores() : void {
	this.sistemaService.resetaMSGs();
	
	let animalId = this.actRoute.snapshot.paramMap.get( "animalId" );
	
	if ( this.tutorNomeIni.length == 0 ) {
		this.sistemaService.setMensagemErro( "O as iniciais do nome do tutor é um campo requerido." );	
		return;
	}

	this.crudService.filtraTutores( animalId, this.tutorNomeIni ).subscribe(
		dados => {
			this.animal.tutores = dados;
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  registraTutor() : void {
	this.sistemaService.resetaMSGs();
	
	let animalId = this.actRoute.snapshot.paramMap.get( "animalId" );
	
	this.crudService.registraTutor( animalId, this.tutorCad ).subscribe(
		dados => {
			this.resetaTutorFormRegistro();
			this.filtraTutores();
			
			this.sistemaService.setMensagemOk( "Tutor registrado com êxito" );
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  visualizaTutor( id : any ) : void {
	let animalId = this.actRoute.snapshot.paramMap.get( "animalId" );

	this.sistemaService.redireciona( [ 'tutor', animalId, id ] );		
  }
      
  deletaTutor( id : any ): void {
	this.sistemaService.resetaMSGs();

	this.crudService.deletaTutor( id ).subscribe(
		dados => {
			this.filtraTutores();
			this.sistemaService.setMensagemOk( "Tutor removido!" );			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  resetaTutorFormRegistro() : void {
	this.tutorCad.pessoa.nome = "";
	this.tutorCad.pessoa.telefone = "";
	this.tutorCad.pessoa.email = "";
  }

}

