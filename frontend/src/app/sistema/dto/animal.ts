
import { Tutor } from './tutor';

export class Animal {
	id : number = 0;
	nome: string = "";
	especie: string = "";
	raca : string = "";
	dataNascimento : any = null;
	tutores: Tutor[] = new Array();
}