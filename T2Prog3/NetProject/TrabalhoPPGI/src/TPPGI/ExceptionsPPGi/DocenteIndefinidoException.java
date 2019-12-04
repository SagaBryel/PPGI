package TPPGI.ExceptionsPPGi;

/** Exception para busca de docente inexistente/indefinido 
 * @author Gabriel Paschoal
 * @author Hiuri Liberato
 */
public class DocenteIndefinidoException extends ExceptionsPPGI{
    public DocenteIndefinidoException(String titulo, String sigla){
        super("Código de docente não definido usado na publicação \"" + titulo + "\": " + sigla + ".");
    }
}
