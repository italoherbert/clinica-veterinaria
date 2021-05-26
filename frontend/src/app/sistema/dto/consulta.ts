
import { Veterinario } from './veterinario';
import { Animal } from './animal';

export class Consulta {
	id : number = 0;
	dataConsulta : any = null;
	status : string = "";
	veterinario : Veterinario = new Veterinario();
	animal : Animal = new Animal();
}