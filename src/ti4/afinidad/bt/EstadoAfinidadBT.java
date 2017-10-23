package ti4.afinidad.bt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ti4.afinidad.Cliente;
import ti4.afinidad.ProblemaAfinidad;
import us.lsi.bt.EstadoBT;

public class EstadoAfinidadBT implements EstadoBT<Map<String, String>,Integer>{
	
	private int index;
	private Map<Integer, Integer> asignacion;
	private int numAfinidad;
	private List<Integer> trabajadoresEnUso;
	private List<Cliente> clientes;
	private List<String> trabajadores;
	
	public EstadoAfinidadBT(int numAfinidad, int index){
		super();
		this.numAfinidad = numAfinidad;
		this.index = index;
		this.asignacion = new HashMap<Integer, Integer>();
		this.trabajadoresEnUso = new ArrayList<Integer>();
		this.clientes = ProblemaAfinidad.clientes;
		this.trabajadores = ProblemaAfinidad.trabajadores;
	}
	
	public EstadoAfinidadBT(){
		this.index = 0;
		this.numAfinidad = 0;
		this.asignacion = new HashMap<Integer, Integer>();
		this.trabajadoresEnUso = new ArrayList<Integer>();
		this.clientes = ProblemaAfinidad.clientes;
		this.trabajadores = ProblemaAfinidad.trabajadores;
	}
	
	@Override
	public void avanza(Integer a) {
		asignacion.put(index, a);
		trabajadoresEnUso.add(a);
		numAfinidad++;
		index++;
	}

	public void retrocede(Integer a) {
		asignacion.remove(index);
		trabajadoresEnUso.remove(a);
		numAfinidad--;
		index--;
	}

	public int size() {
		return this.clientes.size() - index;
	}

	public boolean isFinal() {
		return index ==clientes.size()-1;
	}

	public List<Integer> getAlternativas() {
		List<Integer> res = new ArrayList<Integer>();
		if(trabajadoresEnUso.isEmpty()){
			for(String s: trabajadores){
				res.add(trabajadores.indexOf(s));
			}
		} else {
			for(int i = 0; i < trabajadores.size(); i++){
				if(trabajadoresEnUso.contains(i) && Collections.frequency(trabajadoresEnUso, i) < 3 && clientes.get(i).franjaHoraria != clientes.get(index).franjaHoraria){
					res.add(i);
				} else {
					if(!trabajadoresEnUso.contains(i)){
						res.add(i);
					}
				}
			}
		}
		
		return res;
	}

	@Override
	public Map<String, String> getSolucion() {
		Map<String, String> mapa = new HashMap<String, String>();
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(asignacion.keySet());
		List<Integer> values = new ArrayList<Integer>();
		values.addAll(asignacion.values());
		for(int i = 0; i < asignacion.size(); i++){
			Integer a = keys.get(i);
			Integer b = values.get(i);
			mapa.put(clientes.get(a).nombre, trabajadores.get(b));
		}
		return mapa;
	}

	@Override
	public Double getObjetivo() {
		return (double) numAfinidad;
	}

	@Override
	public Double getObjetivoEstimado(Integer a) {
		return Double.MAX_VALUE;
	}
}
