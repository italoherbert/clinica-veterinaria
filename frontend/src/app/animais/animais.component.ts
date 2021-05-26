import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router, ActivatedRoute } from '@angular/router';

import { Animal } from "../sistema/dto/animal";
import { AnimalCad } from "../sistema/dto/animal-cad";

import { CrudService } from "../sistema/crud.service";
import { SistemaService } from "../sistema/sistema.service";
import { Const } from "../const";

@Component({
  selector: 'app-animais',
  templateUrl: './animais.component.html',
  styleUrls: ['./animais.component.css']
})
export class AnimaisComponent implements OnInit {

  animais: Animal[] = new Array();
  animalCad: AnimalCad = new AnimalCad();
  
  especies: string[] = new Array();
  
  nomeIni: string = "*";
	
  constructor( private route : Router, private actRoute : ActivatedRoute, private crudService : CrudService, public sistemaService : SistemaService ) { }

  ngOnInit(): void {
	this.sistemaService.resetaMSGs();
	
	this.carregaEspecies();
	
	this.filtraAnimais();	
  }
  
  registraAnimal() {	  
 	this.sistemaService.resetaMSGs();
	
	if ( this.animalCad.especie == "DEFAULT" ) {
		this.sistemaService.setMensagemErro( "Selecione uma espécie de animal." );
		return;
	}
 		
	this.crudService.registraAnimal( this.animalCad ).subscribe(
		response => {
			this.resetaFormRegistro();
			this.filtraAnimais();
			this.sistemaService.setMensagemOk( "Animal cadastrado." );
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }    
  
  carregaEspecies() : void {
	this.crudService.listaEspecies().subscribe( 
		dados => {
			this.especies = dados;
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
	
  filtraAnimais() : void {
	this.sistemaService.resetaMSGs();
	
	if ( this.nomeIni.length == 0 ) {
		this.sistemaService.setMensagemErro( "O as iniciais do nome do animal é um campo requerido." );	
		return;
	}

	this.crudService.filtraAnimais( this.nomeIni ).subscribe(
		dados => {
			this.animais = dados;

			this.sistemaService.setMensagemOk( "Animais filtrados com êxito!" );			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  visualiza( id : any ) : void {
	this.sistemaService.redireciona( [ 'animal', id ] );
  }
    
  deleta( id : any ): void {
	this.sistemaService.resetaMSGs();

	this.crudService.deletaAnimal( id ).subscribe(
		dados => {
			this.filtraAnimais();
			this.sistemaService.setMensagemOk( "Animal removido!" );			
		},
		err => {
			this.sistemaService.processaBackendErro( err );
		}
	);
  }
  
  resetaFormRegistro() : void {
	this.animalCad.nome = "";
	this.animalCad.especie = "";
	this.animalCad.raca = "";
	this.animalCad.dataNascimento = "";
  }

}
