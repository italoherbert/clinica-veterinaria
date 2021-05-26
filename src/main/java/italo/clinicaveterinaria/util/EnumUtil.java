package italo.clinicaveterinaria.util;

import org.springframework.stereotype.Component;

import italo.clinicaveterinaria.model.ConsultaStatus;
import italo.clinicaveterinaria.model.Especie;

@Component
public class EnumUtil {
	
	private final String[] ESPECIES_TIPOS = {
		String.valueOf( Especie.CANIDEOS ),
		String.valueOf( Especie.FELINOS ),
		String.valueOf( Especie.BOVINOS ),
		String.valueOf( Especie.OVINOS ),
		String.valueOf( Especie.CAPRINOS )
	};
	
	private final String[] CONSULTA_STATUS_TIPOS = {
		String.valueOf( ConsultaStatus.AGENDADA ),
		String.valueOf( ConsultaStatus.REALIZADA ) 
	};

	public Especie paraEspecie( String especie ) {
		if ( especie.equalsIgnoreCase( String.valueOf( Especie.CANIDEOS ) ) ) {
			return Especie.CANIDEOS;
		} else if ( especie.equalsIgnoreCase( String.valueOf( Especie.FELINOS ) ) ) {
			return Especie.FELINOS;
		} else if ( especie.equalsIgnoreCase( String.valueOf( Especie.BOVINOS ) ) ) {
			return Especie.BOVINOS;
		} else if ( especie.equalsIgnoreCase( String.valueOf( Especie.OVINOS ) ) ) {
			return Especie.OVINOS;
		} else if ( especie.equalsIgnoreCase( String.valueOf( Especie.CAPRINOS ) ) ) {
			return Especie.CAPRINOS;
		}
		return null;
	}
	
	public ConsultaStatus paraConsultaStatus( String status ) {
		if ( status.equalsIgnoreCase( String.valueOf( ConsultaStatus.AGENDADA ) ) ) {
			return ConsultaStatus.AGENDADA;
		} else if ( status.equalsIgnoreCase( String.valueOf( ConsultaStatus.REALIZADA ) ) ) {
			return ConsultaStatus.REALIZADA;
		}
		return null;
	}
		
	public String[] getEspeciesTipos() {
		return ESPECIES_TIPOS;
	}
	
	public String[] getConsultaStatusTipos() {
		return CONSULTA_STATUS_TIPOS;
		
	}
	
}
