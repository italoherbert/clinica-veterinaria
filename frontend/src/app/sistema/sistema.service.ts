import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SistemaService {
		
  mensagemOk : any = "";
  mensagemErro : any = "";
  	    
  constructor( private http : HttpClient, private router: Router ) { }
    
  ngOnInit(): void {
	this.resetaMSGs();
  }
  	        		
  redireciona( path : any ) : void {
	this.router.navigate( path );
  }

  resetaMSGs() : void {
	this.mensagemErro = "";
	this.mensagemOk = "";
  }
        
  processaBackendErro( err : any ) : void {	 
	this.mensagemErro = this.getBackendErro( err );
	window.scrollTo( 0, 0 );
  }
  
  setMensagemOk( msg : any ) : void {
	this.mensagemOk = msg;
	window.scrollTo( 0, 0 );
  }	  
  
  setMensagemErro( msg : any ): void {
	this.mensagemErro = msg;
	window.scrollTo( 0, 0 );
  }
  
  getBackendErro( err : any ) : string {       
	alert( err.status+"  "+err.error.codigo );
	switch( err.status ) {
	  case 400:
		switch( err.error.codigo ) {
			case 100: return "Consulta não encontrada.";
			case 101: return "Data consulta inválida.";
			case 102: return "Tipo de status de consulta desconhecido.";
			case 103: return "Data da consulta requerida.";
			case 104: return "Selecione um veterinário.";
			case 105: return "Selecione um animal.";

			case 200: return "Animal não encontrado.";
			case 201: return "Data de nascimento em formato inválido.";
			case 202: return "Nome do animal é um campo requerido.";
			case 203: return "Especie do animal é um campo requerido.";
			case 204: return "Raça do animal é um campo requerido.";
			case 205: return "Data de nascimento do animal é um campo requerido.";
			case 206: return "Tipo de espécie de animal desconhecido.";

			case 300: return "Veterinário não encontrado.";
			case 301: return "Nome do veterinário é um campo obrigatório.";
			case 302: return "Telefone do veterinário é um campo obrigatório.";		  
			case 303: return "E-Mail do veterinário é um campo obrigatório.";

			case 400: return "Tutor não encontrado.";
			case 401: return "Nome do tutor é um campo obrigatório.";
			case 402: return "Telefone do tutor é um campo obrigatório.";		  
			case 403: return "E-Mail do tutor é um campo obrigatório.";
		}
		break;
	  case 401:	   			
		return "Você não tem autorização para utilizar esse recurso!";	
	  case 403:
		return "É necessária autenticação para utilizar esse recurso!";
	}
	
	return "Erro desconhecido!";
  }
    
  getFrontendErro( erro : any ): string {
    this.resetaMSGs();
	switch( erro ) {
		case 1: return "Nome é um campo obrigatório";	
		case 2: return "Selecione um animal";	
		case 3: return "Selecione um veterinário";	
	}
	return "Erro desconhecido!";
  }
  
  isMostrarMensagemErro() : boolean {
	return this.mensagemErro.length > 0;
  }
  
  isMostrarMensagemOk() : boolean {
	return this.mensagemOk.length > 0;
  }
  
}
