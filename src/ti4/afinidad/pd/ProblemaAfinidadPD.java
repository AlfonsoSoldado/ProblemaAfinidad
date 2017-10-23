package ti4.afinidad.pd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ti4.afinidad.Cliente;
import ti4.afinidad.ProblemaAfinidad;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;

public class ProblemaAfinidadPD  implements ProblemaPD<Map<String, String>, Integer> {

	private int index;
	
	private int afinidadAcum;
	
	private List<String> trabajadores;
	private List<Cliente> clientes;
	private List<Integer> trabajadoresEnUso;
	
	public static ProblemaAfinidadPD create(String fichero){
		ProblemaAfinidad.create(fichero);
		return new ProblemaAfinidadPD();
		
	}
	
	public ProblemaAfinidadPD(){
		this.index = 0;
		this.trabajadoresEnUso = new ArrayList<Integer>();
		this.clientes = ProblemaAfinidad.clientes;
		this.trabajadores = ProblemaAfinidad.trabajadores;
	}
	
	public ProblemaAfinidadPD(int index, List<Integer> trabajadoresEnUso) {
		this.index = index;
		this.trabajadoresEnUso = trabajadoresEnUso;
		this.clientes = ProblemaAfinidad.clientes;
		this.trabajadores = ProblemaAfinidad.trabajadores;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.Max;
	}

	@Override
	public int size() {
		return clientes.size() - index;
	}

	@Override
	public boolean esCasoBase() {
		return index == clientes.size()-1 || afinidadAcum == clientes.size();
	}

	@Override
	public Sp<Integer> getSolucionCasoBase() {
		return Sp.create(0, afinidadAcum*1.);
	}

	@Override
	public Sp<Integer> seleccionaAlternativa(List<Sp<Integer>> ls) {
		Sp<Integer> res = null;
		for(Sp<Integer> s: ls){
			if(s.propiedad != null){
				res = s;
			}
		}
		return res;
	}

	@Override
	public ProblemaPD<Map<String, String>, Integer> getSubProblema(Integer a,
			int np) {		
		List<Integer> trabajadoresOcup = new ArrayList<>();
		trabajadoresOcup.addAll(trabajadoresEnUso);
	    trabajadoresOcup.add(a);
	    if(clientes.get(index).trabajadoresAfines.contains(trabajadores.get(a))){
	      afinidadAcum++;
	    }
	    
	    return new ProblemaAfinidadPD(this.index+1,trabajadoresOcup);
	}

	@Override
	public Sp<Integer> combinaSolucionesParciales(Integer a,
			List<Sp<Integer>> ls) {
		Sp<Integer> res = ls.get(0);
		Double valor = a * afinidadAcum + res.propiedad;
		return Sp.create(a, valor);
	}

	@Override
	public List<Integer> getAlternativas() {
		List<Integer> res = new ArrayList<Integer>();
		
		for(int i = 0; i < trabajadores.size(); i++){
			if(trabajadoresEnUso.contains(i) && Collections.frequency(trabajadoresEnUso, i) < 3 && clientes.get(i).franjaHoraria != clientes.get(index).franjaHoraria){
				res.add(i);
			} else {
				if(!trabajadoresEnUso.contains(i)){
					res.add(i);
				}
			}
		}
		return res;
	}

	@Override
	public int getNumeroSubProblemas(Integer a) {
		return 1;
	}

	@Override
	public Map<String, String> getSolucionReconstruida(Sp<Integer> sp) {
		Map<String, String> mapa = new HashMap<String,String>();
		mapa.put(ProblemaAfinidad.clientes.get(index).nombre, ProblemaAfinidad.trabajadores.get(sp.alternativa));
		return mapa;
	}

	@Override
	public Map<String, String> getSolucionReconstruida(Sp<Integer> sp,
			List<Map<String, String>> ls) {
//		Map<String, String> mapa = ls.get(0);
		Map<String, String> mapa = new HashMap<String,String>();
		mapa.put(ProblemaAfinidad.clientes.get(index).nombre, ProblemaAfinidad.trabajadores.get(sp.alternativa));
		return mapa;
	}

	@Override
	public Double getObjetivoEstimado(Integer a) {
		return Double.MAX_VALUE;
	}

	@Override
	public Double getObjetivo() {
		return (double) afinidadAcum;
	}
	
	
	
	
	
}
