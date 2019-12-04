package TPPGI.ExceptionsPPGi;

/** Exception para Códigos repetidos na inserção em coleções
 * @author Gabriel Paschoal
 * @author Hiuri Liberato
 */
public class CodigoRepetidoException extends ExceptionsPPGI{
    public CodigoRepetidoException(String objeto, String codigo){
        super("Código repetido para " + objeto + ": " + codigo + ".");
    }
}
