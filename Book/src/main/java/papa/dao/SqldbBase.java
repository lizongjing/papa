/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package papa.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
/**
 * ���ݵײ����
 * @author xj
 */
public abstract class SqldbBase {

    /**
     * Release Database resource. Example: close(con);
     * @param obj
     */
    public static void close(Object obj) {
		closeObject(obj);
	}

    /**
     * Release Database resource. Example: close(ps, rs);
     * @param obj1
     * @param obj2
     */
    public static void close(Object obj1, Object obj2) {
        closeObject(obj1);
        closeObject(obj2);
	}

    /**
     * Release resource
     * @param obj
     */
    public static void closeObject(Object obj) {
		if(obj == null)
			return;
		try {
			if(obj instanceof Connection)
				((Connection)obj).close();
			if(obj instanceof PreparedStatement)
				((PreparedStatement)obj).close();
            if(obj instanceof Statement)
                ((Statement)obj).close();
			if(obj instanceof ResultSet)
				((ResultSet)obj).close();
		}
        catch(Exception e) {}
		obj = null;
	}
}