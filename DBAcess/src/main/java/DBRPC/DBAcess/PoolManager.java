/**
 * 
 */
package DBRPC.DBAcess;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jinyu
 * 管理连接
 */
public class PoolManager {
    public static PoolManager getInstance(){
        PoolManager dBService = null;
        if (dBService == null) {
            dBService = new PoolManager();
        }
        return dBService;
    }
    private  ConcurrentHashMap<String,HikariDataSource> map=new ConcurrentHashMap<String,HikariDataSource>();
    Connection conn = null ;
    private String applocaltion="";
    // 调用SQL      
    PreparedStatement pst = null;
    private PoolManager()
    {
        URL url = PoolManager.class.getProtectionDomain().getCodeSource().getLocation();  
        String filePath = null;  
        try {  
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"  
            // 截取路径中的jar包名  
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);  
        }  
          
        File file = new File(filePath);  
        applocaltion = file.getAbsolutePath();//得到windows下的正确路径 
    }
    
    /**
     * 初始化配置连接
     * @param name
     */
 private void initConfig(String name)
 {
     System.out.println("读取DB配置路径："+applocaltion);
     StringBuffer buf=new StringBuffer();
     buf.append(applocaltion);
     buf.append("/config/");
     buf.append(name);
     buf.append("_dbpool.properties");
     HikariConfig config = new HikariConfig(buf.toString());
     HikariDataSource dataSource= new HikariDataSource(config);
     map.put(name, dataSource);
 }
 
 /**
  * 获取连接
  * @param dbName db
  * @return
  *
  */
public Connection getConnection(String dbName)  {
    try {
        HikariDataSource   dataSource=map.getOrDefault(dbName, null);
        if(dataSource==null)
        {
            initConfig(dbName);
        }
        return dataSource.getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }    
   }

/**
 *  停止
 *  关闭所有连接
 */
public void stop()  {
   
    for(HikariDataSource v:map.values())
    {
        v.close();
    }
    map.clear();
}


}
