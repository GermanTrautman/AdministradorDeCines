package com.cine.utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Calendario {

    public enum Formato {
        
        DIA_MES_ANIO("dd/MM/yyyy");
        
        private DateFormat formatter;
        
        private Formato(String pattern) {
            
            formatter = new SimpleDateFormat(pattern, new Locale("es", "ES"));
        }
        
        public String aplicarA(Date fecha) {
            
            return formatter.format(fecha);
        }
    }

    public String formatear(Formato formato, Date fecha) {
        
        return formato != null && fecha != null ? formato.aplicarA(fecha) : null;
    }
}
