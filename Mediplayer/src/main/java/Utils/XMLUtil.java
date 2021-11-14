package Utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Model.Conection;


public class XMLUtil {
	public static String file="conn.xml";
    /**
     * Metodo para cargar datos de la conexion del programa con la base de datos de un archivo .xml
     * @return devuelve un objeto Connection
     */
    public static Conection loadDataXML() {
        Conection result=new Conection();       
        File f=new File(file);
        if(f.canRead()){
            try{
                JAXBContext context=JAXBContext.newInstance(Conection.class);
                Unmarshaller um = context.createUnmarshaller();
                Conection conec = (Conection) um.unmarshal(f);
                result=conec;
            }catch(JAXBException ex){
                ex.printStackTrace();
                result=new Conection();
            }
        }
        return result;
    }
    /**
     * Metodo para guardar datos de la conexion del programa con la base de datos en un archivo .xml
     * @param data Objeto con los datos de la conexion a gurdar
     */
    public static void writeDataXML(Conection data){
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Conection.class);
            Marshaller m=context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Conection conec = new Conection();
            m.marshal(data, new File(file));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            //Dialog.showError("ERROR", "Error reading "+file, ex.toString());
        }
               
    }
}
