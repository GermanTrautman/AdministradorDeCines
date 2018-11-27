package com.cine.utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Calendario {

    public enum Formato {
        
        DIA_MES_ANIO("dd/MM/yyyy");
        
        private DateFormat formatter;
        
        private Formato(String pattern) {
            
            formatter = new SimpleDateFormat(pattern, new Locale("es", "ES"));
        }
        
        public String aplicarA(LocalDate fecha) {
            
            return formatter.format(fecha);
        }
    }

    public String formatear(Formato formato, LocalDate fecha) {
        
        return formato != null && fecha != null ? formato.aplicarA(fecha) : null;
    }
}
