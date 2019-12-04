package TPPGI.ExceptionsPPGi;

/**Exception para Veiculo indefinido 
 * 
 * @author Gabriel Paschoal 
 * @author Hiuri Liberato
 */
public class VeiculoIndefinidoException  extends ExceptionsPPGI{
    /**Construtor da exception que lança a mensagem
     * 
     * @param sigla
     * @param tipo 
     */
    public VeiculoIndefinidoException(String sigla, String tipo){
        super("Tipo de veículo desconhecido para veículo " + sigla + ": " + tipo + ".");
    }
}
