package ti4.afinidad.pd;

import java.util.Map;

import ti4.afinidad.ProblemaAfinidad;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.ProblemaPD;

public class TestAfinidadPD {
	public static void main(String[] args) {
	
		ProblemaAfinidad.create("ficheros/afinidad_test2.txt");
//		ProblemaAfinidad.createEjemplo();
		
		ProblemaPD<Map<String,String>, Integer> p= new ProblemaAfinidadPD();//TODO
		AlgoritmoPD<Map<String,String>, Integer> a= Algoritmos.createPD(p);
		a.ejecuta();
		a.showAllGraph(AlgoritmoPD.getRaiz()+"PDAfinidad.gv","Afinidad",p);

		if (a.solucionesParciales.get(p) != null){
			System.out.println("Afinidad: " + a.solucionesParciales.get(p).propiedad);
			System.out.println(a.getSolucion(p));
		}else{
			System.out.println("No hay solución.");
		}
			
		
	}
}
