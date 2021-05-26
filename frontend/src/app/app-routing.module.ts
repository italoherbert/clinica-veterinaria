import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AnimaisComponent } from './animais/animais.component';
import { VeterinariosComponent } from './veterinarios/veterinarios.component';
import { ConsultasComponent } from './consultas/consultas.component';

import { ConsultaComponent } from './consulta/consulta.component';
import { AnimalComponent } from './animal/animal.component';
import { VeterinarioComponent } from './veterinario/veterinario.component';
import { TutorComponent } from './tutor/tutor.component';

const routes: Routes = [
	{ path: '', redirectTo: 'consultas', pathMatch: 'full' },

	{ path: 'animais', component : AnimaisComponent },
	{ path: 'veterinarios', component : VeterinariosComponent },
	{ path: 'consultas', component : ConsultasComponent },

	{ path: 'consulta/:consultaId', component : ConsultaComponent },
	{ path: 'animal/:animalId', component : AnimalComponent },
	
	{ path: 'veterinario/:veterinarioId', component : VeterinarioComponent },
	{ path: 'tutor/:animalId/:tutorId', component : TutorComponent }
	
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
