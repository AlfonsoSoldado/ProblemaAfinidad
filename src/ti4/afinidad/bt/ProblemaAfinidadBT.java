package ti4.afinidad.bt;

import java.util.Map;



import us.lsi.bt.EstadoBT;
import us.lsi.bt.ProblemaBT;

public class ProblemaAfinidadBT  implements ProblemaBT<Map<String, String>, Integer>{
	
	public static ProblemaBT<Map<String, String>, Integer> create() {
		return new ProblemaAfinidadBT();
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.Max;
	}

	@Override
	public EstadoBT<Map<String, String>, Integer> getEstadoInicial() {
		return new EstadoAfinidadBT();
	}
	
	
}
