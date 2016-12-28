
package pdtp;

import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ClientAdminRemote extends ClientRemote{
     boolean logOff();
        ArrayList getUsernameInscritos();

    public ArrayList getUsernamesOnline();
}
