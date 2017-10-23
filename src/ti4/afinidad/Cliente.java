package ti4.afinidad;

import java.util.Arrays;
import java.util.List;

public class Cliente {
	public String nombre;
	public int franjaHoraria;
	public List<String> trabajadoresAfines;
	public Cliente(String nombre, int franjaHoraria, List<String> trabajadoresAfines) {
		super();
		this.nombre = nombre;
		this.franjaHoraria = franjaHoraria;
		this.trabajadoresAfines = trabajadoresAfines;
	}
	public Cliente(String l) {
		//System.out.println(l);
		String[] data= l.split(",");
		if(data.length!=3) throw new IllegalArgumentException("Incorrecto formato para un Cliente "+l+". Debería ser nombre,franjaHoraria,trabajadoresAfines.");
		
		nombre= data[0];
		franjaHoraria= Integer.parseInt(data[1]);
		trabajadoresAfines= Arrays.asList(data[2].split(";"));
	}
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", franjaHoraria=" + franjaHoraria + ", trabajadoresAfines="
				+ trabajadoresAfines + "]";
	}
	
	
	
		
}
