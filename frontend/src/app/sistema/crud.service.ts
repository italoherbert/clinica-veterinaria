import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Consulta } from './dto/consulta';
import { Animal } from './dto/animal';
import { Veterinario } from './dto/veterinario';
import { Tutor } from './dto/tutor';

import { ConsultaCad } from './dto/consulta-cad';
import { AnimalCad } from './dto/animal-cad';
import { VeterinarioCad } from './dto/veterinario-cad';
import { TutorCad } from './dto/tutor-cad';

import { ConsultaFiltro } from './dto/consulta-filtro';

@Injectable({
  providedIn: 'root'
})
export class CrudService {

	private apiBaseUrl = "/api";

	constructor( private http : HttpClient ) { }
		
	registraConsulta( consulta : ConsultaCad ) : Observable<any> {
		return this.http.post( this.apiBaseUrl+"/consulta/registra", consulta );
	}
	
	filtraConsultas( consultaFiltro : ConsultaFiltro ) : Observable<any> {
		return this.http.post( this.apiBaseUrl+"/consulta/filtra/", consultaFiltro );
	}
	
	getConsulta( consultaId : any ) : Observable<any> {
		return this.http.get( this.apiBaseUrl+"/consulta/get/"+consultaId );
	}
	
	salvaStatus( consultaId : any, status : any ) : Observable<any> {
		return this.http.patch( this.apiBaseUrl+"/consulta/salva/status/"+consultaId, status );
	}
	
	listaStatuss() : Observable<any> {
		return this.http.get( this.apiBaseUrl+"/consulta/lista/statuss" );
	}
	
	deletaConsulta( id : any ) : Observable<any> {
		return this.http.delete( this.apiBaseUrl+"/consulta/deleta/"+id );
	}
	
	registraAnimal( animalCad : AnimalCad ) : Observable<any> {
		return this.http.post( this.apiBaseUrl+"/animal/registra", animalCad );
	}
	
	filtraAnimais( nomeIni : any ) : Observable<any> {
		return this.http.get( this.apiBaseUrl+"/animal/filtra/"+nomeIni );
	}
	
	getAnimal( id : any ) : Observable<any> {
		return this.http.get( this.apiBaseUrl+"/animal/get/"+id );
	}
	
	listaEspecies() : Observable<any> {
		return this.http.get( this.apiBaseUrl+"/animal/lista/especies" );
	}
	
	deletaAnimal( id : any ) : Observable<any> {
		return this.http.delete( this.apiBaseUrl+"/animal/deleta/"+id );
	}
	
	registraVeterinario( veterinarioCad : VeterinarioCad ) : Observable<any> {
		return this.http.post( this.apiBaseUrl+"/veterinario/registra", veterinarioCad );
	}
	
	getVeterinario( veterinarioId : any ) : Observable<any> {
		return this.http.get( this.apiBaseUrl+"/veterinario/get/"+veterinarioId );
	}
	
	filtraVeterinarios( nomeIni : any ) : Observable<any> {
		return this.http.get( this.apiBaseUrl+"/veterinario/filtra/"+nomeIni );
	}
		
	deletaVeterinario( id : any ) : Observable<any> {
		return this.http.delete( this.apiBaseUrl+"/veterinario/deleta/"+id );
	}
	
	registraTutor( animalId : any, tutorCad : TutorCad ) : Observable<any> {
		return this.http.post( this.apiBaseUrl+"/tutor/registra/"+animalId, tutorCad );
	}
	
	getTutor( tutorId : any ) : Observable<any> {
		return this.http.get( this.apiBaseUrl+"/tutor/get/"+tutorId );
	}
	
	filtraTutores( animalId : any, nomeIni : any ) : Observable<any> {
		return this.http.get( this.apiBaseUrl+"/tutor/filtra/"+animalId+"/"+nomeIni );
	}
		
	deletaTutor( id : any ) : Observable<any> {
		return this.http.delete( this.apiBaseUrl+"/tutor/deleta/"+id );
	}
	
}
