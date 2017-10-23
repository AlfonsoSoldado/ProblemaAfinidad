package ti4.afinidad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import us.lsi.stream.Stream2;

/**
 * @author Andres
 *
 *Clase que contiene las propiedades compartidas del problema de la afinidad.
 *
 *Igualmente contiene el método para leer dichas propiedades de un fichero de texto.
 */
public class ProblemaAfinidad {
	public static List<String> trabajadores;
	public static List<Cliente> clientes;	
	
	private ProblemaAfinidad() {
			
		}
	/**
	 * Define un problemaAfinidad de ejemplo con 8 clientes y 3 trabajadores
	 */
	public static ProblemaAfinidad createEjemplo(){
		trabajadores= Lists.newArrayList("Marco","Amparo","Rosa");
		clientes= Lists.newArrayList(
				new Cliente("Juan", 10, Lists.newArrayList("Amparo", "Rosa")),
				new Cliente("Maria", 10, Lists.newArrayList("Rosa")),
				new Cliente("Sara", 11, Lists.newArrayList("Amparo", "Rosa")),
				new Cliente("Andres", 11, Lists.newArrayList("Marco", "Rosa")),
				new Cliente("Antonio", 11, Lists.newArrayList("Marco")),
				new Cliente("Sonia", 12, Lists.newArrayList("Marco")),
				new Cliente("Marta", 12, Lists.newArrayList("Marco")),
				new Cliente("Ivan", 12, Lists.newArrayList("Amparo"))			
				);
		
				
		return  new ProblemaAfinidad();
	}
	
	/**
	 * Define un ProblemaAfinidad cogiendo los datos de un fichero de texto.
	 * 
	 * Cada línea del fichero de texto tendrá la estructura:
	 * nombreCliente, franjaHoraria, trabajadororesAfines
	 * 
	 * Por su lado, trabajadoresAfines serán los nombres de los trabajadores separados por ;
	 * @param file
	 */
	public static ProblemaAfinidad create(String file){
		Set<String> trab= new HashSet<>();			
		clientes=new ArrayList<>();
		
		Stream2.fromFile(file)
			.map(l-> l.replace(" ","")) //quitar espacios en blanco
			.peek(l -> clientes.add(new Cliente(l))) //crear clientes
			.forEach(l-> trab.addAll(Arrays.asList(l.split(",")[2].split(";")))); //crear trabajadores
			
		trabajadores = new ArrayList<>(trab);//Elimina repetidos		
		
		return new ProblemaAfinidad();
	}
	
	
	
	

	
	
	
}
