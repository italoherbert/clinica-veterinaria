package italo.clinicaveterinaria.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DataUtil {

	private final SimpleDateFormat sdfformatDataHora = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
	private final SimpleDateFormat sdfparseDataHora = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	
	private final SimpleDateFormat sdfformatData = new SimpleDateFormat( "dd/MM/yyyy" );
	private final SimpleDateFormat sdfparseData = new SimpleDateFormat( "yyyy-MM-dd" );
	
	public Date paraData( String data ) throws ParseException {
		return sdfparseData.parse( data );
	}
	
	public Date paraDataHora( String data ) throws ParseException {
		return sdfparseDataHora.parse( data );
	}
	
	public String formataData( Date data ) {
		return sdfformatData.format( data );
	}
	
	public String formataDataHora( Date data ) {
		return sdfformatDataHora.format( data ); 
	}
	
}
