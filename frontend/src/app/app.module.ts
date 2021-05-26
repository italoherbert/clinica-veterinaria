import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AnimalComponent } from './animal/animal.component';
import { AnimaisComponent } from './animais/animais.component';
import { VeterinariosComponent } from './veterinarios/veterinarios.component';
import { ConsultasComponent } from './consultas/consultas.component';
import { ConsultaComponent } from './consulta/consulta.component';
import { TutorComponent } from './tutor/tutor.component';
import { VeterinarioComponent } from './veterinario/veterinario.component';

@NgModule({
  declarations: [
    AppComponent,
		
    AnimalComponent,
    AnimaisComponent,
    VeterinariosComponent,
    ConsultasComponent,
    ConsultaComponent,
    TutorComponent,
    VeterinarioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
	HttpClientModule,
	RouterModule,
	FormsModule,
	ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
