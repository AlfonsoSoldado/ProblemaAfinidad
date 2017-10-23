package ti4.afinidad.bt;

import java.util.Map;

import ti4.afinidad.Cliente;
import ti4.afinidad.ProblemaAfinidad;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;
import us.lsi.bt.ProblemaBT;

public class TestAfinidadBT {
	public static void main(String[] args) {

		AlgoritmoBT.soloLaPrimeraSolucion=false;
		AlgoritmoBT.numeroDeSoluciones=Integer.MAX_VALUE;
		
		ProblemaAfinidad.create("ficheros/afinidad_test2.txt");
//		ProblemaAfinidad.createEjemplo();
		ProblemaBT<Map<String,String>, Integer> p = ProblemaAfinidadBT.create();//TODO
		AlgoritmoBT<Map<String,String>, Integer> a= Algoritmos.createBT(p);
		a.ejecuta();	
	
		System.out.println("|||||||AFINIDAD|||||||");
		System.out.println(a.getMejorValor());
		System.out.println("-------------------------");
		System.out.println("|||||||SOLUCIÓN|||||||");
		System.out.println(a.solucion);
		System.out.println("-------------------------");
		System.out.println("|||||||CLIENTES|||||||");
		int acum = 1;
		for(Cliente c: ProblemaAfinidad.clientes){
			System.out.println("Cliente número " + acum + " --->   " + c);
			acum++;
		}
		System.out.println("-------------------------");
	}
}
